package com.lhind.internship.TravelPlans.controller;

import com.lhind.internship.TravelPlans.model.dto.AuthenticationRequest;
import com.lhind.internship.TravelPlans.model.dto.AuthenticationResponse;
import com.lhind.internship.TravelPlans.model.entity.User;
import com.lhind.internship.TravelPlans.service.AuthenticationService;
import com.lhind.internship.TravelPlans.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final UserService userService;

  @RequestMapping(method = RequestMethod.POST, path = "/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest authenticationRequest) {
    return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/register")
  public ResponseEntity<User> register(@RequestBody User user) {
    return ResponseEntity.ok(userService.register(user));
  }
}
