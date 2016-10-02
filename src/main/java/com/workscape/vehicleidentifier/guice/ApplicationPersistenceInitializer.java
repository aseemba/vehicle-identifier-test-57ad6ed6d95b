package com.workscape.vehicleidentifier.guice;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class ApplicationPersistenceInitializer {

  private final PersistService persistService;

  @Inject
  ApplicationPersistenceInitializer(PersistService persistService) {
    this.persistService = persistService;

    // start JPA
    this.persistService.start();
  }

  public void shutdown() {
    //stop JPA
    persistService.stop();
  }
}
