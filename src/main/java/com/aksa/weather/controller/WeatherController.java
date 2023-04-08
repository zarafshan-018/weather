package com.aksa.weather.controller;

import com.aksa.weather.exception.DataNotFoundException;
import com.aksa.weather.model.WeatherApiResponse;
import com.aksa.weather.model.WeatherErrorResponse;
import com.aksa.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather/v1")
public class WeatherController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WeatherService weatherService;

    @GetMapping("/report")
    public ResponseEntity<WeatherApiResponse> getAllDataByDate(@RequestParam("startDate") String startdate, @RequestParam("endDate") String endDate, @RequestParam("city") String city) {
        WeatherApiResponse resp = weatherService.getWeatherReport(startdate, endDate, city);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<WeatherErrorResponse> handleException(DataNotFoundException exc) {

        WeatherErrorResponse error = new WeatherErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<WeatherErrorResponse> handleException(Exception exc) {

        WeatherErrorResponse error = new WeatherErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

