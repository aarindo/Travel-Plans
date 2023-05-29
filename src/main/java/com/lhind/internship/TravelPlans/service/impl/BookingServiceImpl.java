package com.lhind.internship.TravelPlans.service.impl;

import com.lhind.internship.TravelPlans.model.dto.BookingCreateDTO;
import com.lhind.internship.TravelPlans.model.entity.Booking;
import com.lhind.internship.TravelPlans.model.entity.Flight;
import com.lhind.internship.TravelPlans.model.entity.FlightBooking;
import com.lhind.internship.TravelPlans.model.enums.BookingStatus;
import com.lhind.internship.TravelPlans.model.enums.FlightClasses;
import com.lhind.internship.TravelPlans.repository.BookingRepository;
import com.lhind.internship.TravelPlans.repository.FlightBookingRepository;
import com.lhind.internship.TravelPlans.repository.FlightRepository;
import com.lhind.internship.TravelPlans.repository.FlightTypeRepository;
import com.lhind.internship.TravelPlans.repository.UserRepository;
import com.lhind.internship.TravelPlans.service.BookingService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);
  private final BookingRepository bookingRepository;
  private final UserRepository userRepository;
  private final FlightRepository flightRepository;
  private final FlightBookingRepository flightBookingRepository;
  private final FlightTypeRepository flightTypeRepository;

  @Override
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  @Override
  public Booking createBooking(BookingCreateDTO bookingCreateDTO) {
    var user =
        userRepository
            .findUserByEmail(bookingCreateDTO.getUserEmail())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Cannot find user with email " + bookingCreateDTO.getUserEmail()));
    if (bookingCreateDTO.getFlightIds().size() == 0) {
      throw new IllegalArgumentException("Booking should have at least one flight");
    }
    if (bookingCreateDTO.getFlightIds().size() != bookingCreateDTO.getFlightClasses().size()) {
      throw new IllegalArgumentException("Each flight should have a chosen class");
    }
    List<Flight> flights = new ArrayList<>();
    bookingCreateDTO.getFlightIds().stream()
        .forEach(
            el -> {
              var flight =
                  flightRepository
                      .findById(el)
                      .orElseThrow(
                          () -> new IllegalArgumentException("Cannot find flight with id " + el));

              if (LocalDate.now().isBefore(flight.getFlightDate())) {
                throw new IllegalArgumentException(
                    "Flight has already departured at" + flight.getFlightDate());
              }
              flights.add(flight);
            });
    List<FlightClasses> flightClasses = new ArrayList<>();
    bookingCreateDTO.getFlightClasses().stream()
        .forEach(
            el -> {
              flightClasses.add(
                  FlightClasses.fromStr(el)
                      .orElseThrow(
                          () -> new IllegalArgumentException("Cannot find flight class " + el)));
            });

    List<FlightBooking> flightBookings = new ArrayList<>();
    IntStream.range(0, bookingCreateDTO.getFlightIds().size())
        .forEach(
            idx -> {
              var flightTypeFound =
                  flightTypeRepository
                      .findByFlightIdAndFlightClasses(
                          flights.get(idx).getId(), flightClasses.get(idx))
                      .orElseThrow(
                          () ->
                              new IllegalArgumentException(
                                  "Cannot find flight type information for flight "
                                      + flights.get(idx).getId()
                                      + " with class "
                                      + flightClasses.get(idx)));
              var seatsTaken = flightBookingRepository.countAllByFlightId(flights.get(idx).getId());

              if (seatsTaken == flightTypeFound.getTotalSeats()) {
                throw new IllegalArgumentException("There are no available seats");
              }
              FlightBooking flightBooking = new FlightBooking();
              flightBooking.setFlightClasses(flightClasses.get(idx));
              flightBooking.setFlight(flights.get(idx));
              flightBookingRepository.save(flightBooking);
              flightBookings.add(flightBooking);
            });

    Booking booking = new Booking();
    booking.setUser(user);
    booking.setBookingStatus(BookingStatus.UP);
    booking.setFlightBookings(flightBookings);
    bookingRepository.save(booking);
    flightBookings.stream().forEach(el -> el.setBooking(booking));
    flightBookingRepository.saveAll(flightBookings);
    return booking;
  }

  @Override
  public Booking updateBooking(Long id, Booking booking) {
    var bookingFound =
        bookingRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find booking with id " + id));
    return bookingRepository.save(bookingFound);
  }

  @Override
  public Booking cancelBooking(Long id) {
    var booking =
        bookingRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find booking by id " + id));
    booking.setBookingStatus(BookingStatus.CANCELLED_REQ);
    bookingRepository.save(booking);
    return booking;
  }

  @Override
  public List<Booking> findByUserId(Long id) {
    var user =
        userRepository
            .findUserById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find user with id " + id));
    return bookingRepository.findAllByUser(user);
  }

  @Override
  public void deleteBooking(Long id) {
    bookingRepository.delete(bookingRepository.findById(id).get());
  }
}
