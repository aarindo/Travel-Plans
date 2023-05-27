package com.lhind.internship.TravelPlans.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookingCreateDTO {
  private String userEmail;
  List<Long> flightIds;
  List<String> flightClasses;
  private String bookingStatus;
}
