package com.workscape.vehicleidentifier.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "VEHICLE_TYPE")
public class VehicleType extends IdentifiableObject implements Serializable{
  private static final long serialVersionUID = 1L;

  @Column(name = "`TYPE`")
  private String type;

  @OneToOne(mappedBy = "vehicleType")
  private Frame frame;

  @OneToOne(mappedBy = "vehicleType")
  private Powertrain powertrain;

  @OneToMany(mappedBy = "vehicleType")
  private List<Wheel> wheels = new ArrayList<Wheel>();

  @Transient
  private String vehicleId;

  public VehicleType() {

  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Frame getFrame() {
    return frame;
  }

  public void setFrame(Frame frame) {
    this.frame = frame;
  }

  public Powertrain getPowertrain() {
    return powertrain;
  }

  public void setPowertrain(Powertrain powertrain) {
    this.powertrain = powertrain;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  public List<Wheel> getWheels() {
    return wheels;
  }

  public void setWheels(List<Wheel> wheels) {
    this.wheels = wheels;
  }
}
