package com.lhind.internship.TravelPlans.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lhind.internship.TravelPlans.model.enums.AirlineCode;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FlightDTO {
  private AirlineCode airlineCode;
  private String flightNumber;
  private String origin;
  private String destination;
  @JsonFormat(pattern = "HH-mm")
  private LocalTime departureTime;
  private List<BookingDTO> bookingDTOS = new ArrayList<>();
}
