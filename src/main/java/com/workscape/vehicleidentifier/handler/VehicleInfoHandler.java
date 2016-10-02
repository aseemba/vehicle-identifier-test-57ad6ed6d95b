package com.workscape.vehicleidentifier.handler;

import com.workscape.vehicleidentifier.model.Frame;
import com.workscape.vehicleidentifier.model.Powertrain;
import com.workscape.vehicleidentifier.model.VehicleType;
import com.workscape.vehicleidentifier.model.Wheel;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class VehicleInfoHandler extends DefaultHandler {

  private static final String VEHICLES_TAG = "vehicles";
  private static final String VEHICLE_TAG = "vehicle";
  private static final String ID_TAG = "id";
  private static final String FRAME_TAG = "frame";
  private static final String MATERIAL_TAG = "material";
  private static final String WHEELS_TAG = "wheels";
  private static final String WHEEL_TAG = "wheel";
  private static final String POSITION_TAG = "position";
  private static final String POWERTRAIN_TAG = "powertrain";
  private static final String HUMAN_TAG = "human";

  private final Stack<String> tagsStack = new Stack<String>();
  private final StringBuilder tempVal = new StringBuilder();

  private List<VehicleType> vehicleTypes;
  private VehicleType vehicleType;
  private Frame frame;
  private Powertrain powertrain;
  private List<Wheel> wheels;
  private Wheel wheel;

  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    pushTag(qName);
    tempVal.setLength(0);
    if (VEHICLES_TAG.equalsIgnoreCase(qName)) {
      vehicleTypes = new ArrayList<VehicleType>();
    } else if (VEHICLE_TAG.equalsIgnoreCase(qName)) {
      vehicleType = new VehicleType();
    } else if (FRAME_TAG.equalsIgnoreCase(qName)) {
      frame = new Frame();
    } else if (WHEELS_TAG.equalsIgnoreCase(qName)) {
      wheels = new ArrayList<Wheel>();
    } else if (WHEEL_TAG.equalsIgnoreCase(qName)) {
      wheel = new Wheel();
    } else if (POWERTRAIN_TAG.equalsIgnoreCase(qName)) {
      powertrain = new Powertrain();
    }
  }

  public void characters(char ch[], int start, int length) {
    tempVal.append(ch, start, length);
  }

  public void endElement(String uri, String localName, String qName) {
    String tag = peekTag();
    if (!qName.equals(tag)) {
      throw new InternalError();
    }

    popTag();
    String parentTag = peekTag();

    if (ID_TAG.equalsIgnoreCase(tag)) {
      vehicleType.setVehicleId(tempVal.toString());
    } else if (MATERIAL_TAG.equalsIgnoreCase(tag)) {
      String material = tempVal.toString().trim();
      if (FRAME_TAG.equalsIgnoreCase(parentTag)) {
        frame.setMaterial(material);
      } else if (WHEEL_TAG.equalsIgnoreCase(parentTag)) {
        wheel.setMaterial(material);
      }
    } else if (POSITION_TAG.equalsIgnoreCase(tag)) {
      wheel.setWheelPosition(tempVal.toString());
    } else if (HUMAN_TAG.equalsIgnoreCase(tag)) {
      powertrain.setType(tag);
    } else if (WHEEL_TAG.equalsIgnoreCase(tag)) {
      wheels.add(wheel);
    } else if (VEHICLE_TAG.equalsIgnoreCase(tag)) {
      vehicleType.setFrame(frame);
      vehicleType.setPowertrain(powertrain);
      vehicleType.setWheels(wheels);
      vehicleTypes.add(vehicleType);
    }
  }

  public void startDocument() {
    pushTag("");
  }

  public List<VehicleType> getVehicleTypes() {
    return vehicleTypes;
  }

  private void pushTag(String tag) {
    tagsStack.push(tag);
  }

  private String popTag() {
    return tagsStack.pop();
  }

  private String peekTag() {
    return tagsStack.peek();
  }
}
