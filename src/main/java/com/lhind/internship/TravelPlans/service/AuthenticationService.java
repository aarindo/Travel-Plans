package com.lhind.internship.TravelPlans.service;

import com.lhind.internship.TravelPlans.model.dto.AuthenticationRequest;
import com.lhind.internship.TravelPlans.model.dto.AuthenticationResponse;

public interface AuthenticationService {
  AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
