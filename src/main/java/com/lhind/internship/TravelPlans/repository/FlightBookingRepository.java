package com.lhind.internship.TravelPlans.repository;

import com.lhind.internship.TravelPlans.model.entity.FlightBooking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
  int countAllByFlightId(Long id);

  boolean existsByFlightId(Long id);

  List<FlightBooking> findAllByFlightId(Long id);
}
