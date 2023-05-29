package com.lhind.internship.TravelPlans.service.impl;

import com.lhind.internship.TravelPlans.model.entity.User;
import com.lhind.internship.TravelPlans.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository
        .findUserByEmail(email)
        .map(this::toUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
  }

  private UserDetails toUserDetails(User user) {
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .roles(user.getRole().name())
        .build();
  }
}
