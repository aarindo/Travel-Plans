package com.lhind.internship.TravelPlans.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lhind.internship.TravelPlans.model.enums.AirlineCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "AirlineCode", nullable = false)
    private AirlineCode airlineCode;

    @Column(name = "FlightNumber", nullable = false, unique = true, length = 5)
    private String flightNumber;

    @Column(name = "Origin", nullable = false, length = 3)
    private String origin;

    @Column(name = "Destination", nullable = false, length = 3)
    private String destination;

    @Column(name = "Date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date flightDate;

    @Column(name = "Time", nullable = false)
    @JsonFormat(pattern = "HH-mm")
    private Date departureTime;

    @Column(name = "AircraftType")
    private String aircraftType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "Flight", orphanRemoval = true)
    private FlightClass flightClass;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "flights")
    private List<Booking> bookings;
}
