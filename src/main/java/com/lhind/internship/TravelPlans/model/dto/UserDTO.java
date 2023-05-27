package com.lhind.internship.TravelPlans.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO {
  private String firstname;
  private String middleName;
  private String lastname;
  private String email;
  private BookingDTO bookingDTOS;
}
