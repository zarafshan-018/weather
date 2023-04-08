package com.aksa.weather.repository;

import com.aksa.weather.model.WeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport, Long> {
    @Query(value = "SELECT * FROM weather w WHERE w.date BETWEEN ?1 AND ?2 AND w.location_id = (SELECT location_id FROM location l WHERE l.city=?3)", nativeQuery = true)
    List<WeatherReport> findByDate(String startDate, String endDate, String city);
}
