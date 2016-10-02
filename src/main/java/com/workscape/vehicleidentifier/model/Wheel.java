package com.workscape.vehicleidentifier.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WHEEL")
public class Wheel extends IdentifiableObject implements Serializable {
  private static final long serialVersionUID = 1L;

  @OneToOne
  @JoinColumn(name = "VEHICLE_TYPE_ID")
  private VehicleType vehicleType;

  @Column(name = "`POSITION`")
  private String wheelPosition;

  @Column(name = "MATERIAL")
  private String material;

  public Wheel() {

  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public String getWheelPosition() {
    return wheelPosition;
  }

  public void setWheelPosition(String wheelPosition) {
    this.wheelPosition = wheelPosition;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }
}
