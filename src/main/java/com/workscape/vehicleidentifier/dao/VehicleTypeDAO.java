package com.workscape.vehicleidentifier.dao;

import com.workscape.vehicleidentifier.model.VehicleType;

import java.util.List;

public interface VehicleTypeDAO extends BaseDAO<VehicleType, String>{
  List<VehicleType> findVehicleTypeByProperties(String material, String powetrainType);
}
