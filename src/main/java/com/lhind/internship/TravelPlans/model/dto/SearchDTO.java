package com.lhind.internship.TravelPlans.model.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SearchDTO {
  private String origin;
  private String destination;
  private Date flightDate;
  private String airlineCode;
}
