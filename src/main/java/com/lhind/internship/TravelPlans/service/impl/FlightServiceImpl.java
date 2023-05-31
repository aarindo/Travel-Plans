package com.lhind.internship.TravelPlans.service.impl;

import com.lhind.internship.TravelPlans.model.dto.SearchDTO;
import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.entity.FlightBooking;
import com.lhind.internship.TravelPlans.model.entity.User;
import com.lhind.internship.TravelPlans.model.enums.AirlineCode;
import com.lhind.internship.TravelPlans.repository.FlightBookingRepository;
import com.lhind.internship.TravelPlans.repository.FlightRepository;
import com.lhind.internship.TravelPlans.service.FlightService;
import com.lhind.internship.TravelPlans.util.ValidationUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);
  private final FlightRepository flightRepository;
  private final FlightBookingRepository flightBookingRepository;
  private final ValidationUtil validationUtil;

  @Override
  public List<Flight> getAllFlights() {
    return flightRepository.findAll();
  }

  @Override
  public Flight createFlight(Flight flight) {
    if (validationUtil.isDestinationCorrect(flight)
        && validationUtil.isFlightDateCorrect(flight)
        && validationUtil.isDepartureTimeCorrect(flight)) return flightRepository.save(flight);
    else throw new IllegalArgumentException("Cannot create flight with wrong data");
  }

  @Override
  public Flight updateFlight(Long id, Flight flight) {
    var flightFound =
        flightRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find flight with id " + id));
    if (flightBookingRepository.existsByFlightId(id)) {
      if (validationUtil.isDepartureTimeCorrect(flight)) {
        flightFound.setDepartureTime(flight.getDepartureTime());
        return flightRepository.save(flightFound);
      } else throw new IllegalArgumentException("Cannot create flight with wrong departure time");
    } else {
      if (validationUtil.isDestinationCorrect(flight)
              && validationUtil.isFlightDateCorrect(flight)
              && validationUtil.isDepartureTimeCorrect(flight)) return flightRepository.save(flight);
      else throw new IllegalArgumentException("Cannot create flight with wrong data");
    }
  }

  @Override
  public Flight getFlightById(Long id) {
    return flightRepository.findFlightById(id);
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
