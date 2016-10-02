package com.workscape.vehicleidentifier.utilities;

import org.apache.commons.dbcp.BasicDataSource;
import org.h2.tools.RunScript;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class H2Utilities {
  /**
   * Helper method to initialize the in-memory h2 database.
   */
  public static Connection initializeInMemoryDB() {
    try {
      Class.forName("org.h2.Driver");

      return DriverManager.getConnection(
          "jdbc:h2:mem:vehicle_identifier;" 
              + "DB_CLOSE_DELAY=-1;" 
              + "INIT=runscript from 'classpath:schemaDDL.sql'", "sa", ""
      );
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Helper method to insert test data in the in-memory h2 database.
   */
  public static Connection insertDataInMemoryDB() {
    try {
      Class.forName("org.h2.Driver");
      return DriverManager
          .getConnection(
              "jdbc:h2:mem:vehicle_identifier;"
                  + "DB_CLOSE_DELAY=-1;"
                  + "INIT=runscript from 'classpath:seedData.sql'", "sa", ""
          );
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Helper method to destroy the in-memory h2 database.
   */
  public static void shutdownInMemoryDB() {
    try {
      RunScript.execute(
          "jdbc:h2:mem:vehicle_identifier;DB_CLOSE_DELAY=-1", "sa", "", "classpath:shutdown.sql",
          Charset.defaultCharset(),
          false
      );
    } catch (SQLException e) {
    }
  }
}
