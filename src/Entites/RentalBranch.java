package Entites;

import Entites.enums.VehicleTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentalBranch {
    private String branchName;
    private List<VehicleTypes> supportedVehicleTypes;

    public RentalBranch(String branchName){
        this.branchName = branchName;
    }

    public RentalBranch (String branchName,List<VehicleTypes> supportedVehicleTypes){
        this(branchName);
        this.supportedVehicleTypes = supportedVehicleTypes;
    }

    public String getBranchName() {
        return branchName;
    }

    public List<VehicleTypes> getSupportedVehicleTypes() {
        return supportedVehicleTypes;
    }
}
