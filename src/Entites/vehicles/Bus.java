package Entites.vehicles;

import Entites.Vehicle;
import Entites.enums.VehicleTypes;

public class Bus extends Vehicle {
    public Bus(String id,String parentBranch,Double price) {
        super(id,parentBranch,price,VehicleTypes.BUS);
    }

}
