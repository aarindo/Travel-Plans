package com.lhind.internship.TravelPlans.service;

import com.lhind.internship.TravelPlans.model.dto.BookingCreateDTO;
import com.lhind.internship.TravelPlans.model.entity.Booking;
import java.util.List;

public interface BookingService {
  List<Booking> getAllBookings();

  Booking createBooking(BookingCreateDTO bookingCreateDTO);

  Booking cancelBooking(Long id);

  List<Booking> findByUserId(Long userId);

  void deleteBooking(Long id);
}
