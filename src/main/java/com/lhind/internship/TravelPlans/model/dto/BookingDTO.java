package com.lhind.internship.TravelPlans.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookingDTO {
    private boolean cancellationRequest;
    private String cancellationAnswer;
}
