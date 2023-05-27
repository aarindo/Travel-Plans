package com.lhind.internship.TravelPlans.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lhind.internship.TravelPlans.model.enums.RoleEnum;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "firstname", nullable = false)
  private String firstname;

  @Column(name = "middlename")
  private String middleName;

  @Column(name = "lastname", nullable = false)
  private String lastname;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "phone")
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "role", nullable = false)
  private RoleEnum role;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonBackReference
  private List<Booking> bookings = new ArrayList<>();
}
