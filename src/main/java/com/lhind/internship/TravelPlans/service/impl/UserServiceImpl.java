package com.lhind.internship.TravelPlans.service.impl;

import com.lhind.internship.TravelPlans.model.entity.User;
import com.lhind.internship.TravelPlans.repository.UserRepository;
import com.lhind.internship.TravelPlans.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User createUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User updateUser(Long id, User user) {
    return getUser(user);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findUserByEmail(email).get();
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.delete(userRepository.findById(id).get());
  }

  @Override
  public User register(User user) {
    if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
      throw new IllegalArgumentException("An user with this email address already exists");
    }
    return getUser(user);
  }

  private User getUser(User user) {
    user.setFirstname(user.getFirstname());
    user.setMiddleName(user.getMiddleName());
    user.setLastname(user.getLastname());
    user.setAddress(user.getAddress());
    user.setEmail(user.getEmail());
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setPhoneNumber(user.getPhoneNumber());
    user.setAddress(user.getAddress());
    user.setRole(user.getRole());
    return userRepository.save(user);
  }
}
