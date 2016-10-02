package com.workscape.vehicleidentifier.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POWERTRAIN")
public class Powertrain extends IdentifiableObject implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "TYPE")
  private String type;

  @OneToOne
  @JoinColumn(name = "VEHICLE_TYPE_ID")
  private VehicleType vehicleType;

  public Powertrain() {

  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
