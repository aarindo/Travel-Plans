package com.lhind.internship.TravelPlans.service.impl;

import com.lhind.internship.TravelPlans.model.dto.SearchDTO;
import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.enums.AirlineCode;
import com.lhind.internship.TravelPlans.repository.FlightBookingRepository;
import com.lhind.internship.TravelPlans.repository.FlightRepository;
import com.lhind.internship.TravelPlans.service.FlightService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);
  private final FlightRepository flightRepository;
  private final FlightBookingRepository flightBookingRepository;

  @Override
  public List<Flight> getAllFlights() {
    return flightRepository.findAll();
  }

  @Override
  public Flight createFlight(Flight flight) {
    return flightRepository.save(flight);
  }

  @Override
  public Flight updateFlight(Long id, Flight flight) {
    var flightFound =
        flightRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find flight with id " + id));
    if (flightBookingRepository.existsByFlightId(id)) {
      flightFound.setDepartureTime(flight.getDepartureTime());
      return flightRepository.save(flightFound);
    } else {
      return flightRepository.save(flight);
    }
  }

  @Override
  public List<Flight> searchFlights(SearchDTO searchDTO) {
    if (searchDTO.getAirlineCode() == null || searchDTO.getAirlineCode().isEmpty()) {
      return flightRepository.findByOriginAndDestinationAndFlightDate(
          searchDTO.getOrigin(), searchDTO.getDestination(), searchDTO.getFlightDate());
    }
    return flightRepository.findByOriginAndDestinationAndFlightDateAndAirlineCode(
        searchDTO.getOrigin(), searchDTO.getDestination(),
        searchDTO.getFlightDate(), AirlineCode.fromStr(searchDTO.getAirlineCode()).get());
  }

  @Override
  public void deleteFlight(Long id) {
    var flightFound =
        flightRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find flight with id " + id));
    if (flightBookingRepository.existsByFlightId(id)) {
      throw new IllegalArgumentException("Cannot delete already booked flight with id " + id);
    }
    flightRepository.delete(flightFound);
  }
}
