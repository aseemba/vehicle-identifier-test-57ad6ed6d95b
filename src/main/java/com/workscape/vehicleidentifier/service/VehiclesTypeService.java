package com.workscape.vehicleidentifier.service;

import com.workscape.vehicleidentifier.model.VehicleType;

import java.util.List;
import java.util.Map;

public interface VehiclesTypeService {
  Map<String,String> findVehicleTypes(List<VehicleType> vehicleTypeInfoList);
}
