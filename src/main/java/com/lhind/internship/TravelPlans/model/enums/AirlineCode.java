package com.lhind.internship.TravelPlans.model.enums;

import java.util.Optional;

public enum AirlineCode {
  LH,
  OS,
  LX,
  EW;

  public static Optional<AirlineCode> fromStr(String str) {
    return switch (str) {
      case "LH" -> Optional.of(LH);
      case "OS" -> Optional.of(OS);
      case "LX" -> Optional.of(LX);
      case "EW" -> Optional.of(EW);
      default -> Optional.empty();
    };
  }
}
