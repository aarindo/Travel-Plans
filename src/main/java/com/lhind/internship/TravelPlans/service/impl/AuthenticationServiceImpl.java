package com.lhind.internship.TravelPlans.service.impl;

import com.lhind.internship.TravelPlans.model.dto.AuthenticationRequest;
import com.lhind.internship.TravelPlans.model.dto.AuthenticationResponse;
import com.lhind.internship.TravelPlans.service.AuthenticationService;
import com.lhind.internship.TravelPlans.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final TokenService tokenService;

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest)
      throws AuthenticationException {
    final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authenticationRequest.getEmail(), authenticationRequest.getPassword()));
    authenticationResponse.setToken(
        tokenService.generateToken(
            userDetailsService.loadUserByUsername(authenticationRequest.getEmail())));
    return authenticationResponse;
  }
}
