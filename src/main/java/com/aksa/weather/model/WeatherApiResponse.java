package com.aksa.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
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
@Embeddable
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
class Temperature  {
    @JsonProperty("temp")
    private Double temp;

}

