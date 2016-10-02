package com.workscape.vehicleidentifier.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OutputServiceImplTest {
  private OutputServiceImpl outputService;
  private String outputFile = "src/test/resources/output.txt";
  private Map<String, String> vehicleTypesMap;

  @Before
  public void setUp() throws Exception {
    outputService = new OutputServiceImpl();
    vehicleTypesMap = new HashMap<String, String>();
  }

  @After
  public void tearDown() throws Exception {
    outputService = null;
    File file = new File(outputFile);
    file.delete();
  }

  @Test
  public void testCreateVehicleTypesReport() throws IOException {
    vehicleTypesMap.put("a1", "Big Wheel");
    outputService.createVehicleTypesReport(vehicleTypesMap, outputFile);
    File file = new File(outputFile);
    assertTrue(file.exists());
    assertEquals("a1 is a Big Wheel", FileUtils.readFileToString(file, "UTF-8").trim());
  }
}
