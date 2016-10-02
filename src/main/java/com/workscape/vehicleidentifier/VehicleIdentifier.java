package com.workscape.vehicleidentifier;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.workscape.vehicleidentifier.guice.ApplicationPersistenceInitializer;
import com.workscape.vehicleidentifier.guice.VehicleIdentifierModule;
import com.workscape.vehicleidentifier.model.VehicleType;
import com.workscape.vehicleidentifier.service.InputService;
import com.workscape.vehicleidentifier.service.OutputService;
import com.workscape.vehicleidentifier.service.VehiclesTypeService;
import com.workscape.vehicleidentifier.service.impl.OutputServiceImpl;
import com.workscape.vehicleidentifier.utilities.H2Utilities;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Vehicle Identifier main class.
 * 
 */
public class VehicleIdentifier {
	public static void main(String[] args) throws Exception{
		Injector injector = Guice.createInjector(new VehicleIdentifierModule());
		ApplicationPersistenceInitializer initializer = injector.getInstance(ApplicationPersistenceInitializer.class);

		initializeReferenceData();

		String fileName =  "src/main/resources/vehicles.xml";
		if(args.length > 0 && StringUtils.isNotBlank(args[0])) {
			fileName = args[0];
		}
		File inputFile = new File(fileName);
		InputService inputService = injector.getInstance(InputService.class);
		List<VehicleType> vehicleTypeInfoList = inputService.readInputVehicleInfo(inputFile);

		VehiclesTypeService vehicleTypeDAO = injector.getInstance(VehiclesTypeService.class);
		Map<String, String> vehicleTypesMap = vehicleTypeDAO.findVehicleTypes(vehicleTypeInfoList);

		final String outputFile = "target/outputReport.txt";
		OutputService outputService = injector.getInstance(OutputServiceImpl.class);
		outputService.createVehicleTypesReport(vehicleTypesMap, outputFile);

		H2Utilities.shutdownInMemoryDB();
		initializer.shutdown();
	}

	private static void initializeReferenceData() {
		H2Utilities.initializeInMemoryDB();
		H2Utilities.insertDataInMemoryDB();
	}
}
