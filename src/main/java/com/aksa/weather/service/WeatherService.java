package com.aksa.weather.service;

import javax.persistence.*;

@Entity
@Table(name = "weather")
public class WeatherService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String date;

    @Column(name = "description")
    private String time;

    @Column(name = "published")
    private Double temp;

}
