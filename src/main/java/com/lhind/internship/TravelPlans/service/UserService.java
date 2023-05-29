package com.lhind.internship.TravelPlans.service;

import com.lhind.internship.TravelPlans.model.entity.User;
import java.util.List;

public interface UserService {
  List<User> getAllUsers();

  User createUser(User user);

  User updateUser(Long id, User user);

  User findByEmail(String email);

  void deleteUser(Long id);

  User register(User user);
}
