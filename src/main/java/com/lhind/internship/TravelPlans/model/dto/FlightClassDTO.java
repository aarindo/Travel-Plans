package com.lhind.internship.TravelPlans.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FlightClassDTO {
    private Integer economy;
    private Integer premiumEconomy;
    private Integer business;
    private Integer firstClass;
}
