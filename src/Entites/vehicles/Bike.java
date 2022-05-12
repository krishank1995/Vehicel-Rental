package Entites.vehicles;

import Entites.Vehicle;
import Entites.enums.VehicleTypes;

public class Bike extends Vehicle {
    public Bike(String id,String parentBranch,Double price) {
        super(id,parentBranch,price,VehicleTypes.BIKE);
    }
}
