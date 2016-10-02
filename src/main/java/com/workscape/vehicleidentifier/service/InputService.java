package com.workscape.vehicleidentifier.service;

import com.workscape.vehicleidentifier.model.VehicleType;

import java.io.File;
import java.util.List;

public interface InputService {
  List<VehicleType> readInputVehicleInfo(File inputFile);
}
