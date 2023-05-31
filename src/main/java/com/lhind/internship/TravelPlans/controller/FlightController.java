package com.lhind.internship.TravelPlans.controller;

import com.lhind.internship.TravelPlans.model.dto.BookingDTO;
import com.lhind.internship.TravelPlans.model.dto.FlightDTO;
import com.lhind.internship.TravelPlans.model.dto.SearchDTO;
import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.entity.FlightBooking;
import com.lhind.internship.TravelPlans.service.FlightService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flights")
@AllArgsConstructor
@Schema
public class FlightController {

  private ModelMapper modelMapper;
  FlightService flightService;

  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.GET, value = "/get")
  public ResponseEntity<List<FlightDTO>> getAllFlights() {
      List<Flight> flights = flightService.getAllFlights();
      List<FlightDTO> flightDTOs = getFlightData(flights);
      return ResponseEntity.ok(flightDTOs);
  }

  @PreAuthorize("hasRole('ADMIN') OR hasRole('TRAVELLER')")
  @RequestMapping(method = RequestMethod.GET, value = {"/search"})
  public ResponseEntity<List<FlightDTO>> searchFlights(@RequestBody SearchDTO searchDTO) {
      List<Flight> flights = flightService.searchFlights(searchDTO);
      List<FlightDTO> flightDTOs = getFlightData(flights);
      return ResponseEntity.ok(flightDTOs);
  }

  @PreAuthorize(value = "hasRole('ADMIN')")
  @GetMapping("/users/{id}")
  public ResponseEntity<FlightDTO> getFlightById(@PathVariable("id") Long id) {
      Flight flight = flightService.getFlightById(id);
      if (flight != null) {
          FlightDTO flightDTO = getFlightData(Collections.singletonList(flight)).get(0);
          return ResponseEntity.ok(flightDTO);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }

    private List<FlightDTO> getFlightData(List<Flight> flights) {
        return flights.stream()
                .map(flight -> {
                    FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);
                    List<BookingDTO> bookingDTOs = flight.getFlightBookings().stream()
                            .map(FlightBooking::getBooking)
                            .map(booking -> {
                                BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
                                List<Long> flightIds = booking.getFlightBookings().stream()
                                        .map(flightBooking -> flightBooking.getFlight().getId())
                                        .collect(Collectors.toList());
                                List<String> flightClasses = booking.getFlightBookings().stream()
                                        .map(flightBooking -> flightBooking.getFlightClasses().name())
                                        .collect(Collectors.toList());
                                bookingDTO.setFlightIds(flightIds);
                                bookingDTO.setFlightClasses(flightClasses);
                                return bookingDTO;
                            })
                            .collect(Collectors.toList());
                    flightDTO.setBookingDTOS(bookingDTOs);
                    return flightDTO;
                })
                .collect(Collectors.toList());
    }

  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.POST, value = {"/add"})
  public ResponseEntity<FlightDTO> createFlight(@RequestBody Flight flight) {
    flightService.createFlight(flight);
    return ResponseEntity.ok(modelMapper.map(flight, FlightDTO.class));
  }

  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.POST, value = {"/update/{id}"})
  public ResponseEntity<FlightDTO> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
    flightService.updateFlight(id, flight);
    return ResponseEntity.ok(modelMapper.map(flight, FlightDTO.class));
  }
  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
  public void deleteFlight(@PathVariable Long id) {
    flightService.deleteFlight(id);
  }
}
