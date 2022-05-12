package Entites.vehicles;

import Entites.Vehicle;
import Entites.enums.VehicleTypes;

public class Car extends Vehicle {
    public Car(String id,String parentBranch,Double price) {
        super(id,parentBranch,price, VehicleTypes.CAR);
    }
}
