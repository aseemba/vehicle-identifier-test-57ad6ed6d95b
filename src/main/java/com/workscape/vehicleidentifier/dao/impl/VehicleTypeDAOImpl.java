package com.workscape.vehicleidentifier.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.workscape.vehicleidentifier.dao.VehicleTypeDAO;
import com.workscape.vehicleidentifier.model.VehicleType;

import java.util.List;

import javax.persistence.EntityManager;

public class VehicleTypeDAOImpl extends BaseDAOImpl<VehicleType, String> implements VehicleTypeDAO{

  @Inject
  VehicleTypeDAOImpl(final Provider<EntityManager> emProvider) {
    super(VehicleType.class, emProvider);
  }

  public List<VehicleType> findVehicleTypeByProperties(String material, String powetrainType) {
    return createQuery("FROM VehicleType v WHERE "
        + "v.frame.material = :material AND "
        + "v.powertrain.type = :powertrainType")
        .setParameter("material", material)
        .setParameter("powertrainType", powetrainType)
        .asList();
  }
}
