package com.lhind.internship.TravelPlans.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lhind.internship.TravelPlans.model.enums.FlightClasses;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight_type")
public class FlightType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "total_seats")
  private Integer totalSeats;

  @Column(name = "flight_class")
  @Enumerated(value = EnumType.STRING)
  @NotNull
  FlightClasses flightClasses;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_id", referencedColumnName = "id")
//  @JsonBackReference
  private Flight flight;
}
