package com.lhind.internship.TravelPlans.service;

import com.lhind.internship.TravelPlans.model.dto.SearchDTO;
import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.entity.User;
import java.util.List;

public interface FlightService {
  List<Flight> getAllFlights();

  List<Flight> searchFlights(SearchDTO searchDTO);

  Flight createFlight(Flight flight);

  Flight updateFlight(Long id, Flight flight);

  List<User> getUsersByFlightId(Long id);

  void deleteFlight(Long id);
}
