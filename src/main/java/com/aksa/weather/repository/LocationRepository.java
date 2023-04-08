package com.aksa.weather.repository;

import com.aksa.weather.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface LocationRepository extends JpaRepository<Location, Long> {

}
