package com.lhind.internship.TravelPlans.repository;

import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.enums.AirlineCode;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
  List<Flight> findByOriginAndDestinationAndFlightDate(
      String origin, String destination, LocalDate flightDate);

  List<Flight> findByOriginAndDestinationAndFlightDateAndAirlineCode(
      String origin, String destination, LocalDate flightDate, AirlineCode airlineCode);

  Flight findFlightById(Long id);
}
