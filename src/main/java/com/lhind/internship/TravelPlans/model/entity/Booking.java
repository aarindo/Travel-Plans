package com.lhind.internship.TravelPlans.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "CancellationRequest")
    private boolean cancellationRequest;

    @Column(name = "CancellationAnswer")
    private String cancellationAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id", referencedColumnName = "Id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "Book_Flight",
            joinColumns = @JoinColumn(name = "Booking_Id"),
            inverseJoinColumns = @JoinColumn(name = "Flight_Id")
    )
    private List<Flight> flights;
}
