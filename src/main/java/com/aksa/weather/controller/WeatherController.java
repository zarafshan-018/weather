package com.aksa.weather.controller;

import com.aksa.weather.model.WeatherApiResponse;
import com.aksa.weather.wrapper.ReportList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/weather/v1")
public class WeatherController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/report")
    public WeatherApiResponse getAllDataByDate() {

        String URL = "https://api.openweathermap.org/data/2.5/forecast?q=Islamabad&units=metric&appid=ba3f4a9beef094d25cbc61727dd16bf5";
        WeatherApiResponse responseEntity = restTemplate.getForObject(URL,
                WeatherApiResponse.class);

        return responseEntity;

    }
}

