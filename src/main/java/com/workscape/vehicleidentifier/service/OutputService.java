package com.workscape.vehicleidentifier.service;

import java.util.Map;

public interface OutputService {
  void createVehicleTypesReport(Map<String, String> vehicleTypesMap, String outputFile);
}
