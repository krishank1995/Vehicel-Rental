package Service.impl;

import Common.Constants;
import DAL.impl.RentalBranchRepo;
import DAL.impl.VehicleRepo;
import Entites.RentalBranch;
import Entites.Vehicle;
import Entites.enums.VehicleTypes;
import Entites.vehicles.*;
import Service.VehicleService;

import java.util.Optional;

public class VehicleServiceImpl implements VehicleService {

    private VehicleRepo vehicleRepo;
    private RentalBranchRepo branchRepo;

    public VehicleServiceImpl(){
        this.vehicleRepo = new VehicleRepo();
        this.branchRepo = new RentalBranchRepo();
    }

    public boolean addVehicle(String branchName,String vehicleType,String vehicleId,Double price){
        if(vehicleRepo.get(vehicleId).isPresent()){
            System.out.println(String.format(Constants.VEHICLE_ALREADY_EXISTS,branchName));
            return false;
        }

        Optional<Vehicle> transformedVehicle = createVehicleBasedOnType(vehicleType,vehicleId,branchName,price);
        if(!transformedVehicle.isPresent()){
            System.out.println(String.format(Constants.UNSUPPORTED_VEHICLE_ADDITION,vehicleType));
            return false;
        }

        //If a) Branch does not exist b) Vehicle is of unsupported Type for Branch
        Optional<RentalBranch> rentalBranch = branchRepo.get(branchName);
        if(!rentalBranch.isPresent()){
            System.out.println(String.format(Constants.INVALID_BRANCH,branchName));
            return false;
        }

        if(!rentalBranch.get().getSupportedVehicleTypes().contains(transformedVehicle.get().getType())){
            //System.out.println(String.format(Constants.UNSUPPORTED_VEHICLE_ADDITION_FOR_BRANCH,vehicleType));
            return false;
        }

        vehicleRepo.add(transformedVehicle.get());
        return true;
    }

    private Optional<Vehicle> createVehicleBasedOnType(String type, String vehicleId, String branchName, Double price){
        VehicleTypes typeEnum = null;

        try{
            typeEnum = VehicleTypes.valueOf(type);
        }catch (IllegalArgumentException ex){
            System.out.println(String.format(Constants.UNSUPPORTED_VEHICLE_ADDITION,type));
            return Optional.empty();
        }

        switch (typeEnum){
            case BIKE:
                return Optional.of(new Bike(vehicleId,branchName,price));
            case BUS:
                return Optional.of(new Bus(vehicleId,branchName,price));
            case VAN:
                return Optional.of(new Van(vehicleId,branchName,price));
            case CAR:
                return Optional.of(new Car(vehicleId,branchName,price));
        }

        return Optional.empty();
    }

}
