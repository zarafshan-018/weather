package com.aksa.weather.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "weather")
public class WeatherReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("main")
    @Embedded
    private Temperature temp;
    @JsonProperty("dt_txt")
    @Column(unique=true)
    private String date;

    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    @JsonBackReference
    private Location location;

    @Override
    public String toString(){
        return "";
    }

}
