package com.workscape.vehicleidentifier.service.impl;

import com.google.inject.Inject;
import com.workscape.vehicleidentifier.service.OutputService;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputServiceImpl implements OutputService{
  @Inject
  OutputServiceImpl() {

  }

  public void createVehicleTypesReport(Map<String, String> vehicleTypesMap, String outputFile) {
    List<String> content = new ArrayList<String>();
    for (Map.Entry<String, String> entry : vehicleTypesMap.entrySet())
    {
      content.add(entry.getKey() + " is a " + entry.getValue());
    }

    try {
      FileUtils.writeLines(new File(outputFile), content);
    } catch (IOException e) {
      System.err.println("Problems writing file");
    }
  }
}
