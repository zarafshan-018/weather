package com.aksa.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;
    @JsonProperty("name")
    @Column(unique=true)
    private String city;
    @JsonProperty("country")
    private String Country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    @JsonManagedReference
    private List<WeatherReport> weatherReports;

    public void addWeatherReports(List<WeatherReport> weatherReports) {
        this.weatherReports = weatherReports;
        weatherReports.forEach(weatherReport -> weatherReport.setLocation(this));
    }


}
