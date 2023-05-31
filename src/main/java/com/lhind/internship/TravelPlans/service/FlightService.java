package com.lhind.internship.TravelPlans.service;

import com.lhind.internship.TravelPlans.model.dto.SearchDTO;
import com.lhind.internship.TravelPlans.model.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
  List<Flight> getAllFlights();

  List<Flight> searchFlights(SearchDTO searchDTO);

  Flight createFlight(Flight flight);

  Flight updateFlight(Long id, Flight flight);

  Flight getFlightById(Long id);

  void deleteFlight(Long id);
}
