package Entites.vehicles;

import Entites.Vehicle;
import Entites.enums.VehicleTypes;

public class Van extends Vehicle {
    public Van(String id,String parentBranch,Double price) {
        super(id,parentBranch,price,VehicleTypes.VAN);
    }

}
