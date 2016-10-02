package com.workscape.vehicleidentifier.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FRAME")
public class Frame extends IdentifiableObject implements Serializable{
  private static final long serialVersionUID = 1L;

  @Column(name = "MATERIAL")
  private String material;

  @OneToOne
  @JoinColumn(name = "VEHICLE_TYPE_ID")
  private VehicleType vehicleType;

  public Frame() {

  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }
}
