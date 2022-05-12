package Service.impl;

import Common.Constants;
import DAL.impl.RentalBranchRepo;
import Entites.RentalBranch;
import Entites.enums.VehicleTypes;
import Service.RentalBranchService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentalBranchServiceImpl implements RentalBranchService {

    private RentalBranchRepo branchRepo;
    public RentalBranchServiceImpl(/* Constructor DI : RentalBranchRepo branchRepo*/){
        this.branchRepo = new RentalBranchRepo();
    }

    public boolean addBranch (String branchName, String csvVehicleTypes){
        if(branchRepo.get(branchName).isPresent()){
            System.out.println(String.format(Constants.BRANCH_ALREADY_EXISTS,branchName));
            return false;
        }

        List<String> supportedVehicles = Arrays.asList(csvVehicleTypes.split(","));
        List<VehicleTypes> supportedVehiclesEnum = new ArrayList<>();

        supportedVehicles.stream().forEach(type -> {
            try{
                VehicleTypes typeEnum = VehicleTypes.valueOf(type);
                supportedVehiclesEnum.add(typeEnum);
            }catch(IllegalArgumentException ex){
                // Handle and log the prompt the user of illegal vehicle type
                System.out.println(String.format(Constants.WRONG_VEHICLE_TYPE,type));
            }

        });

        //If Illegal Vehicle Found don't add the branch and early exit
        if(supportedVehiclesEnum.size()!=supportedVehicles.size()){
            return false;
        }

        branchRepo.add(new RentalBranch(branchName,supportedVehiclesEnum));
        return true;
    }


}
