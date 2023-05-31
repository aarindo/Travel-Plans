package com.lhind.internship.TravelPlans.controller;

import com.lhind.internship.TravelPlans.model.dto.BookingCreateDTO;
import com.lhind.internship.TravelPlans.model.dto.BookingDTO;
import com.lhind.internship.TravelPlans.model.entity.Booking;
import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.entity.FlightBooking;
import com.lhind.internship.TravelPlans.model.enums.FlightClasses;
import com.lhind.internship.TravelPlans.repository.BookingRepository;
import com.lhind.internship.TravelPlans.service.BookingService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@Schema
@Slf4j
public class BookingController {

  private ModelMapper modelMapper;

  BookingService bookingService;
  BookingRepository bookingRepository;
  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.GET, value = "/get")
  public ResponseEntity<List<BookingDTO>> getAllBookings() {
    List<Booking> bookings = bookingService.getAllBookings();
    List<BookingDTO> bookingDTOs = bookings.stream()
            .map(booking -> {
              BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
              List<FlightBooking> flightBookings = booking.getFlightBookings();
              List<Long> flightIds = flightBookings.stream()
                      .map(FlightBooking::getFlight)
                      .map(Flight::getId)
                      .collect(Collectors.toList());
              List<String> flightClasses = flightBookings.stream()
                      .map(FlightBooking::getFlightClasses)
                      .map(FlightClasses::name)
                      .collect(Collectors.toList());
              bookingDTO.setFlightIds(flightIds);
              bookingDTO.setFlightClasses(flightClasses);
              return bookingDTO;
            })
            .collect(Collectors.toList());
    return ResponseEntity.ok(bookingDTOs);
  }
  @PreAuthorize("hasRole('ADMIN') OR hasRole('TRAVELLER')")
  @RequestMapping(method = RequestMethod.POST, value = {"/add"})
  public ResponseEntity<Booking> createBooking(@RequestBody BookingCreateDTO booking) {
    try {
      return ResponseEntity.ok(bookingService.createBooking(booking));
    } catch (IllegalArgumentException ex) {
      log.error(ex.getMessage());
      return ResponseEntity.status(400).build();
    }
  }

  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.POST, value = {"/update/{id}"})
  public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
    bookingService.updateBooking(id, booking);

    Booking updatedBooking = bookingRepository.getBookingById(id);

    BookingDTO bookingDTO = modelMapper.map(updatedBooking, BookingDTO.class);

    List<FlightBooking> flightBookings = updatedBooking.getFlightBookings();
    List<Long> flightIds = flightBookings.stream()
            .map(FlightBooking::getFlight)
            .map(Flight::getId)
            .collect(Collectors.toList());
    List<String> flightClasses = flightBookings.stream()
            .map(FlightBooking::getFlightClasses)
            .map(FlightClasses::name)
            .collect(Collectors.toList());

    bookingDTO.setFlightIds(flightIds);
    bookingDTO.setFlightClasses(flightClasses);

    return ResponseEntity.ok(bookingDTO);
  }

  @PreAuthorize("hasRole('ADMIN') OR hasRole('TRAVELLER')")
  @RequestMapping(method = RequestMethod.POST, value = {"/cancel/{id}"})
  public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
    return ResponseEntity.ok(bookingService.cancelBooking(id));
  }
  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
  public void deleteBooking(@PathVariable Long id) {
    bookingService.deleteBooking(id);
  }
}
