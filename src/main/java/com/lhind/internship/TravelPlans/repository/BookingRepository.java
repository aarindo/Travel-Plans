package com.lhind.internship.TravelPlans.repository;

import com.lhind.internship.TravelPlans.model.entity.Booking;
import com.lhind.internship.TravelPlans.model.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking getBookingById(Long id);
}
