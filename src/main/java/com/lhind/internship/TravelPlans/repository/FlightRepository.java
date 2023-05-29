package com.lhind.internship.TravelPlans.repository;

import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.enums.AirlineCode;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
  List<Flight> findByOriginAndDestinationAndFlightDate(
      String origin, String destination, Date flightDate);

  List<Flight> findByOriginAndDestinationAndFlightDateAndAirlineCode(
      String origin, String destination, Date flightDate, AirlineCode airlineCode);

  Optional<Flight> findById(Long id);
}
