package com.lhind.internship.TravelPlans.model.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
  private String email;
  private String password;
}
