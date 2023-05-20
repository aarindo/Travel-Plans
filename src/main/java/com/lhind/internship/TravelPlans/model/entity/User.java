package com.lhind.internship.TravelPlans.model.entity;

import com.lhind.internship.TravelPlans.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Firstname", nullable = false)
    private String firstname;

    @Column(name = "MiddleName")
    private String middleName;

    @Column(name = "Lastname", nullable = false)
    private String lastname;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Phone")
    private String phoneNumber;

    @Column(name = "Address")
    private String address;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Role", nullable = false)
    private RoleEnum role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Booking> bookings;
}
