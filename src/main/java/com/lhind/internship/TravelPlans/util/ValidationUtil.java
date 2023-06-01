package com.lhind.internship.TravelPlans.util;

import com.lhind.internship.TravelPlans.model.entity.Flight;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ValidationUtil {

  public Boolean isDestinationCorrect(Flight flight) {

    return !flight.getOrigin().equals(flight.getDestination());
  }

  public boolean isFlightDateCorrect(Flight flight) {
    LocalDate today = LocalDate.now();
    LocalDate flightDate = flight.getFlightDate();

    return !flightDate.isBefore(today);
  }

  public boolean isDepartureTimeCorrect(Flight flight) {
    if (isFlightDateCorrect(flight)){
      return true;
    }
    else {
      LocalTime time = LocalTime.now();
      LocalTime departureTime = flight.getDepartureTime();

      return departureTime.isAfter(time);
    }
  }
}
