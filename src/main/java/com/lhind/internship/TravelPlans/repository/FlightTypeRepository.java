package com.lhind.internship.TravelPlans.repository;

import com.lhind.internship.TravelPlans.model.entity.FlightType;
import com.lhind.internship.TravelPlans.model.enums.FlightClasses;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightTypeRepository extends JpaRepository<FlightType, Long> {
  Optional<FlightType> findByFlightIdAndFlightClasses(Long id, FlightClasses flightClasses);
}
