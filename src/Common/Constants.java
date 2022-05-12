package Common;

import Entites.enums.VehicleTypes;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String BRANCH_ALREADY_EXISTS = "%s branch already exists, skipping addition";
    public static final String WRONG_VEHICLE_TYPE = "Cannot add vehicle, Illegal Vehicle Type %s";
    public static final String VEHICLE_ALREADY_EXISTS = "%s vehicle already exists, skipping addition";
    public static final String UNSUPPORTED_VEHICLE_ADDITION = "Unsupported Vehicle Type %s";
    public static final String INVALID_BRANCH = "Invalid Branch %s";
    public static final String UNSUPPORTED_VEHICLE_ADDITION_FOR_BRANCH = "Un-supoorted Vehicle being added : %s to branch";

    public static  Double SURGE_THRESHOLD = 80.0;
    public static final Double SURGE_PERCENT = 1.1;
    public static final Double SURGE_BASE = 1.0;

    public static final List<VehicleTypes> candidatesForSurge = Arrays.asList(VehicleTypes.CAR);
}

