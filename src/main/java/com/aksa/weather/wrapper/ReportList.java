package com.aksa.weather.wrapper;

import com.aksa.weather.model.WeatherApiResponse;

import java.util.ArrayList;
import java.util.List;

public class ReportList {
    List<WeatherApiResponse> weatherApiResponse;

    public ReportList() {
        weatherApiResponse = new ArrayList<>();
    }

    public List<WeatherApiResponse> getWeatherApiResponse() {
        return weatherApiResponse;
    }

    public void setWeatherApiResponse(List<WeatherApiResponse> weatherApiResponse) {
        this.weatherApiResponse = weatherApiResponse;
    }
}
