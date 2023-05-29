package com.lhind.internship.TravelPlans.util;

import com.lhind.internship.TravelPlans.model.entity.Flight;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

  public Boolean isDestinationCorrect(Flight flight) {

    return !flight.getOrigin().equals(flight.getDestination());
  }

  public Boolean isFlightDateCorrect(Flight flight) {
    LocalDate today;
    today = LocalDate.now();
    SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
    dateOnly.format(today);
    return flight.getFlightDate().isAfter(today);
  }

  public Boolean isDepartureTimeCorrect(Flight flight) {
    LocalTime time;
    time = LocalTime.now();
    SimpleDateFormat timeOnly = new SimpleDateFormat("HH:mm");
    timeOnly.format(time);
    return flight.getDepartureTime().isAfter(time);
  }
}
