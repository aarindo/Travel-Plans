package com.lhind.internship.TravelPlans.model.dto;

import com.lhind.internship.TravelPlans.model.enums.AirlineCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FlightDTO {
    private AirlineCode airlineCode;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date departureTime;
}
