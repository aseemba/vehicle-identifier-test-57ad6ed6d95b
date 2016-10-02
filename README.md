# vehicle-identifier-test-57ad6ed6d95b
Vehicle Identifier Test

# To run the project:
1. Build the project using maven from the project root directory. (This will also run the tests as a part of the build process)
  `$> mvn clean install`
2. Once built successfully, you should be able to see a directory called 'target' in the project's parent directory. This contains the executable jar to run the program.
3. Run the program using the command:
	`$> java -jar target/vehicle-identifier-0.0.1-SNAPSHOT.jar`

The program uses vehicles.xml by default if an argument containing the test file path is not given while running the command (3). The contents of vehicles.xml are the sample input. If a different file needs to be passed in as an argument the command can be modified to include the path of the file like so:
	`$> java -jar target/vehicle-identifier-0.0.1-SNAPSHOT.jar /some_path/another_input.xml`

# outputReport.txt
On successful run of the program, the `outputReport.txt` is generated in the `target/` folder of the project to be viewed.
