package com.workscape.vehicleidentifier.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.workscape.vehicleidentifier.model.VehicleType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InputServiceImplTest {
  private InputServiceImpl inputService;

  @Before
  public void setUp() throws Exception {
    inputService = new InputServiceImpl();
  }

  @After
  public void tearDown() throws Exception {
    inputService = null;
  }

  @Test
  public void testReadInputVehicleInfo() {
    File inputFile = new File(getClass().getClassLoader().getResource("vehicles_test.xml").getFile());

    List<VehicleType> vehicleTypes = inputService.readInputVehicleInfo(inputFile);

    assertEquals(1, vehicleTypes.size());

    VehicleType vehicleType = vehicleTypes.get(0);
    assertEquals("vehicle 1", vehicleType.getVehicleId());

    assertNotNull(vehicleType.getFrame());
    assertEquals("plastic", vehicleType.getFrame().getMaterial());

    assertNotNull(vehicleType.getPowertrain());
    assertEquals("human", vehicleType.getPowertrain().getType());

    assertEquals(3, vehicleType.getWheels().size());
    assertEquals("left rear", vehicleType.getWheels().get(0).getWheelPosition());
    assertEquals("plastic", vehicleType.getWheels().get(0).getMaterial());
    assertEquals("right rear", vehicleType.getWheels().get(1).getWheelPosition());
    assertEquals("plastic", vehicleType.getWheels().get(1).getMaterial());
    assertEquals("front", vehicleType.getWheels().get(2).getWheelPosition());
    assertEquals("plastic", vehicleType.getWheels().get(2).getMaterial());
  }
}
