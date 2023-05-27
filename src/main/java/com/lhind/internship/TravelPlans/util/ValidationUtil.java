package com.lhind.internship.TravelPlans.util;

import com.lhind.internship.TravelPlans.model.entity.Flight;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

  public Boolean isDestinationCorrect(Flight flight) {

    if (flight.getOrigin().equals(flight.getDestination())) return false;
    else return true;
  }

  public Boolean isFlightDateCorrect(Flight flight) {
    Date today = new Date();
    SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
    dateOnly.format(today);
    if (flight.getFlightDate().after(today)) return true;
    else return false;
  }

  public Boolean isDepartureTimeCorrect(Flight flight) {
    Date time = new Date();
    SimpleDateFormat timeOnly = new SimpleDateFormat("HH:mm");
    timeOnly.format(time);
    if (flight.getDepartureTime().after(time)) return true;
    else return false;
  }
}
