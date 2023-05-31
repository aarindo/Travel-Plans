package com.lhind.internship.TravelPlans.model.dto;

import com.lhind.internship.TravelPlans.model.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO {
  private String firstname;
  private String middleName;
  private String lastname;
  private String email;
  private List<BookingDTO> bookingDTOS;
}
