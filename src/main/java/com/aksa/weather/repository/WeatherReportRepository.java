package com.aksa.weather.repository;

import com.aksa.weather.model.WeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherReportRepository extends JpaRepository<WeatherReport, Long> {
}
