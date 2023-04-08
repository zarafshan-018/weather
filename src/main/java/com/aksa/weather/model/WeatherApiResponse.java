package com.aksa.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeatherApiResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("list")
    private List<WeatherReport> weatherData;

    @JsonProperty("city")
    Location location;

}


@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
class Temperature  {
    @JsonProperty("temp")
    private Double temp;

}

