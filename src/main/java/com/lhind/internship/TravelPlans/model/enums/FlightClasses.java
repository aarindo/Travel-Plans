package com.lhind.internship.TravelPlans.model.enums;

import java.util.Optional;

public enum FlightClasses {
  ECONOMY,
  PREMIUM_ECONOMY,
  BUSINESS,
  FIRST;

  public static Optional<FlightClasses> fromStr(String str) {
    return switch (str) {
      case "economy", "ECONOMY" -> Optional.of(ECONOMY);
      case "premium_economy", "PREMIUM_ECONOMY" -> Optional.of(PREMIUM_ECONOMY);
      case "business", "BUSINESS" -> Optional.of(BUSINESS);
      case "first", "FIRST" -> Optional.of(FIRST);
      default -> Optional.empty();
    };
  }
}
