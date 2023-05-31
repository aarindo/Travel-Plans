package com.lhind.internship.TravelPlans.controller;

import com.lhind.internship.TravelPlans.model.dto.BookingDTO;
import com.lhind.internship.TravelPlans.model.dto.UserDTO;
import com.lhind.internship.TravelPlans.model.entity.User;
import com.lhind.internship.TravelPlans.service.BookingService;
import com.lhind.internship.TravelPlans.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Schema
public class UserController {

  private ModelMapper modelMapper;

  UserService userService;
  BookingService bookingService;
  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<UserDTO>> getAllUsers() {
      List<User> users = userService.getAllUsers();
      List<UserDTO> userDTOs = users.stream()
              .map(user -> {
                  UserDTO userDTO = modelMapper.map(user, UserDTO.class);
                  List<BookingDTO> bookingDTOs = user.getBookings().stream()
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
                  userDTO.setBookingDTOS(bookingDTOs);
                  return userDTO;
              })
              .collect(Collectors.toList());
      return ResponseEntity.ok(userDTOs);
  }
  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.POST, value = {"/add"})
  public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
    userService.createUser(user);
    return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
  }
  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.POST, value = {"/update/{id}"})
  public ResponseEntity<UserDTO> updateUser( @RequestBody User user, @PathVariable Long id) {
      userService.updateUser(id, user);
      return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
  }
  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.GET, value = "/email/{email}")
  public ResponseEntity<UserDTO> findUserByEmail(@PathVariable("email") String email) {
      User user = userService.findByEmail(email);
      if (user != null) {
          UserDTO userDTO = modelMapper.map(user, UserDTO.class);
          List<BookingDTO> bookingDTOs = user.getBookings().stream()
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
          userDTO.setBookingDTOS(bookingDTOs);
          return new ResponseEntity<>(userDTO, HttpStatus.OK);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
  @PreAuthorize(value = "hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }
}
