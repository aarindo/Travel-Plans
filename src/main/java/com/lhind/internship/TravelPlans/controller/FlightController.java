package com.lhind.internship.TravelPlans.controller;

import com.lhind.internship.TravelPlans.model.dto.FlightDTO;
import com.lhind.internship.TravelPlans.model.dto.SearchDTO;
import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.entity.User;
import com.lhind.internship.TravelPlans.service.FlightService;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
@AllArgsConstructor
@Schema
public class FlightController {

  private ModelMapper modelMapper;
  FlightService flightService;

  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<FlightDTO>> get() {
    return ResponseEntity.ok(
        flightService.getAllFlights().stream()
            .map(flight -> modelMapper.map(flight, FlightDTO.class))
            .collect(Collectors.toList()));
  }

  @PreAuthorize(value = "hasRole('TRAVELER')")
  @RequestMapping(
      method = RequestMethod.GET,
      value = {"/search"})
  public ResponseEntity<List<FlightDTO>> createFlight(@RequestBody SearchDTO searchDTO) {
    return ResponseEntity.ok(
        flightService.searchFlights(searchDTO).stream()
            .map(flight -> modelMapper.map(flight, FlightDTO.class))
            .collect(Collectors.toList()));
  }

  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(
      method = RequestMethod.POST,
      value = {"/add"})
  public ResponseEntity<FlightDTO> createFlight(@RequestBody Flight flight) {
    flightService.createFlight(flight);
    return ResponseEntity.ok(modelMapper.map(flight, FlightDTO.class));
  }

  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(
      method = RequestMethod.POST,
      value = {"/update/{id}"})
  public ResponseEntity<FlightDTO> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
    flightService.updateFlight(id, flight);
    return ResponseEntity.ok(modelMapper.map(flight, FlightDTO.class));
  }

  @PreAuthorize(value = "hasRole('ADMIN')")
  @GetMapping("/users/{id}")
  public List<User> getUsersByFlightId(@PathVariable Long id) {
    return flightService.getUsersByFlightId(id);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
  public void deleteFlight(@PathVariable Long id) {
    flightService.deleteFlight(id);
  }
}
