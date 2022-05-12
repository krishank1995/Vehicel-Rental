
import Service.BookingService;
import Service.RentalBranchService;
import Service.VehicleService;
import Service.impl.BookingServiceImpl;
import Service.impl.RentalBranchServiceImpl;
import Service.impl.VehicleServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        /*
        Problem Summary :

        Rental Service ->
        1. Has 1:M branches across city
        2. Has limited number of different mix of vehicles
        3. Vehicle can be booked in slots (1hr slots in a day) at a fixed price

        Req:
        1. Add a new branch (Done)
        2. Add a vehicle of pre-existing type (Done)
        3. Rent/Book a vehicle (User Params: Vehicle Type, Time Slot ; System Params: Lowest Price, Extension:{Strategy:Higher Price for surge in demand slot?, nX factor) (Done)
        4. Show available vehicles sorted on price at a branch. (Done)
        5. Drop Vehicle from same branch (Done)
        6. Surge pricing as per some business rule as mentioned in point 3 (Done)
        */

        /*
        Input:Output
        Input -> From a file mentioned in args
        Format:
        1. ADD_BRANCH BRANCH_NAME CSV_VEHICLE_TYPES
        2. ADD_VEHICLE BRANCH_NAME VEHICLE_TYPE ID PRICE
        3. BOOK BRANCH_NAME VEHICLE_TYPE START_TIME END_TIME [AS NOT SPECIFIED AND AS PER TEST CASES : ASSUME 24HR Format]
        4. DISPLAY_VEHICLES BRANCH_NAME START_TIME END_TIME


        Output -> Console
        1. ADD_BRANCH -> TRUE/FALSE
        2. ADD_VEHICLE -> TRUE/FALSE
        3. BOOK -> Booking Price on success else -1
        4. DISPLAY_VEHICLES -> CSV VEHICLE_NAMES
        */

       readFileAndInvokeTestCases("./resources/input.txt","./resources/expected_output.txt");
    }

    public static void readFileAndInvokeTestCases(String inputFileName,String expectedOutputFileName){
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);

            File expectedOutputFile = new File(expectedOutputFileName);
            Scanner outputReader = new Scanner(expectedOutputFile);

            while (inputReader.hasNextLine()) {
                String input = inputReader.nextLine();
                String expectedOutput = outputReader.nextLine();
                invokeRentalService(input,expectedOutput);
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void invokeRentalService(String methodCallWithParams,String expectedOutput){
        String format = "Expected:%s ; Output:%s";
        RentalBranchService branchService = new RentalBranchServiceImpl();
        BookingService bookingService = new BookingServiceImpl();
        VehicleService vehicleService = new VehicleServiceImpl();

        String[] split = methodCallWithParams.split(" ");

        String[] outputSplit = expectedOutput.split(";");
        String expectedResult = outputSplit[0];

        switch (split[0]){
            case "ADD_BRANCH":
                boolean branchAdditionResult = branchService.addBranch(split[1], split[2]);
                System.out.println(String.format(format,expectedResult,branchAdditionResult));
                break;
            case "ADD_VEHICLE":
                boolean vehicleAdditionResult = vehicleService.addVehicle(split[1],split[2],split[3],Double.parseDouble(split[4]));
                System.out.println(String.format(format,expectedResult,vehicleAdditionResult));
                break;
            case "BOOK":
                Double bookingResult = bookingService.bookVehicle(split[1],split[2],Integer.parseInt(split[3]),Integer.parseInt(split[4]));
                System.out.println(String.format(format,expectedResult,bookingResult));
                break;
            case "DISPLAY_VEHICLES":
                List<String> availableVehicles = bookingService.displayAvailableVehicleIds(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                System.out.println(String.format(format,expectedResult,String.join(",",availableVehicles)));
                break;
            case "DROP":
                boolean closeBookingResponse = bookingService.closeBooking(split[1]);
                System.out.println(String.format(format,expectedResult,closeBookingResponse));
                break;

            default:
                System.out.println("Invalid Operation");
        }
    }
}
