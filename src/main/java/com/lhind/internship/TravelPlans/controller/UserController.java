package com.lhind.internship.TravelPlans.controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Schema
public class UserController {

  private ModelMapper modelMapper;

  UserService userService;
  BookingService bookingService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<UserDTO>> get() {
    return ResponseEntity.ok(
        userService.getAllUsers().stream()
            .map(user -> modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList()));
  }

  @RequestMapping(
      method = RequestMethod.POST,
      value = {"/add"})
  public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
    userService.createUser(user);
    return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
  }

  @RequestMapping(
      method = RequestMethod.POST,
      value = {"/update/{id}"})
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
    userService.updateUser(id, user);
    return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/email/{email}")
  public ResponseEntity<UserDTO> findUserByEmail(@PathVariable("email") String email) {
    User user = userService.findByEmail(email);
    if (user != null) {
      return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }
}
