package com.lhind.internship.TravelPlans.model.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate flightDate;
  private String airlineCode;
}
