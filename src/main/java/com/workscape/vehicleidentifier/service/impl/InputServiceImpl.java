package com.workscape.vehicleidentifier.service.impl;

import com.google.inject.Inject;
import com.workscape.vehicleidentifier.handler.VehicleInfoHandler;
import com.workscape.vehicleidentifier.model.VehicleType;
import com.workscape.vehicleidentifier.service.InputService;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class InputServiceImpl implements InputService{

  @Inject
  InputServiceImpl() {

  }

  public List<VehicleType> readInputVehicleInfo(File inputFile) {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    try {
      SAXParser saxParser = factory.newSAXParser();
      VehicleInfoHandler vehicleInfoHandler = new VehicleInfoHandler();
      saxParser.parse(inputFile, vehicleInfoHandler);
      return vehicleInfoHandler.getVehicleTypes();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
