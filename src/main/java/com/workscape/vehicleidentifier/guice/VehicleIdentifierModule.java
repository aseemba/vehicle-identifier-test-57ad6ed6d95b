package com.workscape.vehicleidentifier.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.workscape.vehicleidentifier.dao.VehicleTypeDAO;
import com.workscape.vehicleidentifier.dao.impl.VehicleTypeDAOImpl;
import com.workscape.vehicleidentifier.service.InputService;
import com.workscape.vehicleidentifier.service.OutputService;
import com.workscape.vehicleidentifier.service.VehiclesTypeService;
import com.workscape.vehicleidentifier.service.impl.InputServiceImpl;
import com.workscape.vehicleidentifier.service.impl.OutputServiceImpl;
import com.workscape.vehicleidentifier.service.impl.VehiclesTypeServiceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VehicleIdentifierModule extends AbstractModule {
  protected void configure() {
    install (new JpaPersistModule("vehicle-identifier"));

    bind(VehicleTypeDAO.class).to(VehicleTypeDAOImpl.class);

    bind(InputService.class).to(InputServiceImpl.class);
    bind(VehiclesTypeService.class).to(VehiclesTypeServiceImpl.class);
    bind(OutputService.class).to(OutputServiceImpl.class);
  }
}
