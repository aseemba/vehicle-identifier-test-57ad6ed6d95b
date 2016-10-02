package com.workscape.vehicleidentifier.service.impl;

import com.google.inject.Inject;
import com.workscape.vehicleidentifier.dao.VehicleTypeDAO;
import com.workscape.vehicleidentifier.model.VehicleType;
import com.workscape.vehicleidentifier.service.VehiclesTypeService;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehiclesTypeServiceImpl implements VehiclesTypeService {

  private final VehicleTypeDAO vehicleTypeDAO;

  @Inject
  VehiclesTypeServiceImpl(final VehicleTypeDAO vehicleTypeDAO) {
    this.vehicleTypeDAO = vehicleTypeDAO;
  }


  public Map<String, String> findVehicleTypes(List<VehicleType> vehicleTypeInfoList) {
    Map<String, String> vehicleTypesMap = new HashMap<String, String>();

    for (VehicleType vehicleTypeInfo : vehicleTypeInfoList) {
      if(validInput(vehicleTypeInfo)) {
        int wheelsCount = vehicleTypeInfo.getWheels().size();
        List<VehicleType> vehicleTypes = vehicleTypeDAO.findVehicleTypeByProperties(
            vehicleTypeInfo.getFrame().getMaterial(),
            vehicleTypeInfo.getPowertrain().getType());

        if (vehicleTypes.isEmpty()) {
          System.err.println("Vehicle type not found");
        }
        else {
          for (VehicleType vehicleType : vehicleTypes) {
            if (vehicleType.getWheels().size() == wheelsCount
                && StringUtils.equals(
                vehicleType.getWheels().get(0).getMaterial(),
                vehicleTypeInfo.getWheels().get(0).getMaterial())) {
              vehicleTypesMap.put(vehicleTypeInfo.getVehicleId(), vehicleType.getType());
            } else {
              System.err.println("Vehicle type not found");
            }
          }
        }
      } else {
        System.err.println("Vehicle information is insufficient to determine the type");
      }
    }
    return vehicleTypesMap;
  }

  boolean validInput(VehicleType vehicleTypeInfo) {
    return StringUtils.isNotBlank(vehicleTypeInfo.getFrame().getMaterial())
        && StringUtils.isNotBlank(vehicleTypeInfo.getPowertrain().getType());
  }
}
