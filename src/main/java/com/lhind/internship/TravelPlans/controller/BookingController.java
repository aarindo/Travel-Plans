package com.lhind.internship.TravelPlans.controller;

import com.lhind.internship.TravelPlans.model.dto.BookingCreateDTO;
import com.lhind.internship.TravelPlans.model.dto.BookingDTO;
import com.lhind.internship.TravelPlans.model.entity.Booking;
import com.lhind.internship.TravelPlans.service.BookingService;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@Schema
@Slf4j
public class BookingController {

  private ModelMapper modelMapper;

  BookingService bookingService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<BookingDTO>> get() {
    return ResponseEntity.ok(
        bookingService.getAllBookings().stream()
            .map(booking -> modelMapper.map(booking, BookingDTO.class))
            .collect(Collectors.toList()));
  }

  @RequestMapping(
      method = RequestMethod.POST,
      value = {"/add"})
  public ResponseEntity<Booking> createBooking(@RequestBody BookingCreateDTO booking) {
    try {
      return ResponseEntity.ok(bookingService.createBooking(booking));
    } catch (IllegalArgumentException ex) {
      log.error(ex.getMessage());
      return ResponseEntity.status(400).build();
    }
  }

  @RequestMapping(
      method = RequestMethod.POST,
      value = {"/cancel/{id}"})
  public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
    return ResponseEntity.ok(bookingService.cancelBooking(id));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
  public ResponseEntity<List<Booking>> findByUserId(@PathVariable("id") Long id) {
    return ResponseEntity.ok(bookingService.findByUserId(id));
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
  public void deleteBooking(@PathVariable Long id) {
    bookingService.deleteBooking(id);
  }
}
