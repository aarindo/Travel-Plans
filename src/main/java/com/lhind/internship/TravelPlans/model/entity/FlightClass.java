package com.lhind.internship.TravelPlans.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FlightClass")
public class FlightClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Economy")
    private Integer economy = 0;

    @Column(name = "PremiumEconomy")
    private Integer premiumEconomy = 0;

    @Column(name = "Business")
    private Integer business = 0;

    @Column(name = "FirstClass")
    private Integer firstClass = 0;

    @OneToOne
    @JoinColumn(name = "Flight_Id", nullable = false, referencedColumnName = "Id")
    private Flight flight;
}
