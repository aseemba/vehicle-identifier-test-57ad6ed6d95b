package com.workscape.vehicleidentifier.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workscape.vehicleidentifier.dao.VehicleTypeDAO;
import com.workscape.vehicleidentifier.model.Frame;
import com.workscape.vehicleidentifier.model.Powertrain;
import com.workscape.vehicleidentifier.model.VehicleType;
import com.workscape.vehicleidentifier.model.Wheel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VehivlesTypeServiceImplTest {
  private VehiclesTypeServiceImpl vehiclesTypeService;

  @Mock
  private VehicleTypeDAO vehicleTypeDAO;

  @Mock
  private VehicleType vehicleType;

  @Mock
  private Frame frame;

  @Mock
  private Powertrain powertrain;

  @Mock
  private Wheel wheel;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    vehiclesTypeService = new VehiclesTypeServiceImpl(vehicleTypeDAO);
  }

  @After
  public void tearDown() throws Exception {
    wheel = null;
    powertrain = null;
    frame = null;
    vehicleType = null;
    vehicleTypeDAO = null;
    vehiclesTypeService = null;
  }

  @Test
  public void testFindVehicleTypes_validInput() {
    List<VehicleType> vehicleTypes = Arrays.asList(vehicleType);

    when(vehicleType.getFrame()).thenReturn(frame);
    when(frame.getMaterial()).thenReturn("abc");
    when(vehicleType.getPowertrain()).thenReturn(powertrain);
    when(powertrain.getType()).thenReturn("pqr");

    when(vehicleTypeDAO.findVehicleTypeByProperties("abc", "pqr")).thenReturn(vehicleTypes);
    List<Wheel> wheels = Arrays.asList(wheel);
    when(vehicleType.getWheels()).thenReturn(wheels);
    when(wheel.getMaterial()).thenReturn("abc");

    when(vehicleType.getVehicleId()).thenReturn("abc");
    when(vehicleType.getType()).thenReturn("pqr");

    Map<String, String> vehicleTypesMap = vehiclesTypeService.findVehicleTypes(vehicleTypes);

    assertEquals(1, vehicleTypesMap.size());
    assertEquals("pqr", vehicleTypesMap.get("abc"));

    verify(vehicleTypeDAO, times(1)).findVehicleTypeByProperties("abc", "pqr");
  }

  @Test
  public void testFindVehicleTypes_invalidInput_missingPowertrain() {
    List<VehicleType> vehicleTypes = Arrays.asList(vehicleType);

    when(vehicleType.getFrame()).thenReturn(frame);
    when(frame.getMaterial()).thenReturn("abc");
    when(vehicleType.getPowertrain()).thenReturn(powertrain);
    when(powertrain.getType()).thenReturn("");

    Map<String, String> vehicleTypesMap = vehiclesTypeService.findVehicleTypes(vehicleTypes);

    assertTrue(vehicleTypesMap.isEmpty());
    verify(vehicleTypeDAO, never()).findVehicleTypeByProperties("abc", "pqr");
  }

  @Test
  public void testFindVehicleTypes_validInput_typeNotFound() {
    List<VehicleType> vehicleTypes = Arrays.asList(vehicleType);

    when(vehicleType.getFrame()).thenReturn(frame);
    when(frame.getMaterial()).thenReturn("abc");
    when(vehicleType.getPowertrain()).thenReturn(powertrain);
    when(powertrain.getType()).thenReturn("pqr");
    List<Wheel> wheels = Arrays.asList(wheel);
    when(vehicleType.getWheels()).thenReturn(wheels);

    when(vehicleTypeDAO.findVehicleTypeByProperties("abc", "pqr")).thenReturn(new ArrayList<VehicleType>());

    Map<String, String> vehicleTypesMap = vehiclesTypeService.findVehicleTypes(vehicleTypes);

    assertTrue(vehicleTypesMap.isEmpty());
    verify(vehicleTypeDAO, times(1)).findVehicleTypeByProperties("abc", "pqr");
  }
}
