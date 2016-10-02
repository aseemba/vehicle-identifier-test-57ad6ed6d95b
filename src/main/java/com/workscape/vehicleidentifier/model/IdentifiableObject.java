package com.workscape.vehicleidentifier.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IdentifiableObject implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private String id;

  public IdentifiableObject() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
