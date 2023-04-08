package com.aksa.weather.service;

import com.aksa.weather.exception.DataNotFoundException;
import com.aksa.weather.model.Location;
import com.aksa.weather.model.WeatherApiResponse;
import com.aksa.weather.model.WeatherReport;
import com.aksa.weather.repository.LocationRepository;
import com.aksa.weather.repository.WeatherReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    WeatherReportRepository weatherReportRepository;

    @Transactional
    public WeatherApiResponse getWeatherReport(String startDate, String endDate, String city) {
        WeatherApiResponse weatherApiResponse = new WeatherApiResponse();
        //YYYY-MM-DD hh:mm:ss
        startDate =startDate+" 00:00:00";
        endDate = endDate+" 00:00:00";
        List<WeatherReport> weatherReports = weatherReportRepository.findByDate(startDate,endDate,city);
        //if data is present in database
        if(!weatherReports.isEmpty()) {
            Location loc = locationRepository.findByCity(city).get();

            ////setting only location in weatherreport object
            weatherReports = weatherReports.stream().map(weatherReport -> {
                weatherReport.setLocation(loc);
                return weatherReport;
            }).collect(Collectors.toList());

            weatherApiResponse.setWeatherData(weatherReports);
            weatherApiResponse.setLocation(loc);
            return weatherApiResponse;
        }
        //if data is not present in database
        else {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.US);

            LocalDateTime inputStartDate = LocalDate.parse(startDate, dtf).atStartOfDay();
            LocalDateTime inputEndDate = LocalDate.parse(endDate, dtf).atStartOfDay();

            LocalDateTime today = LocalDateTime.of(2023,4,10,00,00); //5
            LocalDateTime maxDate = today.plusDays(5); //10
            //check if date is not valid
            if(inputEndDate.isAfter(maxDate) || inputEndDate.isBefore(today) || inputStartDate.isAfter(maxDate) || inputStartDate.isBefore(today.minusDays(1))) {
                throw new DataNotFoundException("Record not exist. We can forecast for only 5 days in future");
            }
            weatherApiResponse = insertWeatherReport(city);

            return weatherApiResponse;
        }
    }


    @Transactional
    public WeatherApiResponse insertWeatherReport(String city) {
        //make api call
        WeatherApiResponse weatherApiResponse =  getWeatherAPIData(city);

        //save data in database
        List<WeatherReport> weatherData = weatherApiResponse.getWeatherData();
        Location location = weatherApiResponse.getLocation();

        try {
            Optional<Location> locationEntity = locationRepository.findByCity(city);
            if(locationEntity.isEmpty()) {
                Location loc = locationRepository.save(location);
                locationEntity = Optional.of(loc);
            }
            Location loc = locationEntity.get();
            //setting only location in weatherreport object
            weatherData = weatherData.stream().map(weatherReport -> {
                weatherReport.setLocation(loc);
                return weatherReport;
            }).collect(Collectors.toList());
            weatherReportRepository.saveAll(weatherData);
        }
        catch(Exception ex) {
            throw new RuntimeException(ex.getCause());
        }
        return weatherApiResponse;
    }

    public WeatherApiResponse getWeatherAPIData(String city) {
        String URL = "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&units=metric&appid=ba3f4a9beef094d25cbc61727dd16bf5";
        WeatherApiResponse responseEntity = restTemplate.getForObject(URL,
                WeatherApiResponse.class);
        return responseEntity;
    }



}
