package com.lhind.internship.TravelPlans.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lhind.internship.TravelPlans.model.enums.AirlineCode;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "airlinecode", nullable = false)
  private AirlineCode airlineCode;

  @Column(name = "flightnumber", nullable = false, unique = true, length = 5)
  private String flightNumber;

  @Column(name = "origin", nullable = false, length = 3)
  private String origin;

  @Column(name = "destination", nullable = false, length = 3)
  private String destination;

  @Column(name = "date", nullable = false)
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate flightDate;

  @Column(name = "time", nullable = false)
  @JsonFormat(pattern = "HH-mm")
  private LocalTime departureTime;

  @Column(name = "aircrafttype")
  private String aircraftType;

  @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
//  @JsonBackReference
  private List<FlightType> flightTypes = new ArrayList<>();

  @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
//  @JsonBackReference
  List<FlightBooking> flightBookings = new ArrayList<>();
}
