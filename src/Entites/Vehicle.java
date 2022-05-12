package Entites;

import Entites.enums.VehicleTypes;

/*
Specialised entities as booking of Car VS Bus can have evolve into separate process flows.
*/
public abstract class Vehicle {
    private String id;
    private String parentBranch;
    private Double rentalPrice;
    public  VehicleTypes type;
    public Vehicle(String id,String parentBranch,Double rentalPrice,VehicleTypes type){
        this.id = id;
        this.parentBranch = parentBranch;
        this.rentalPrice = rentalPrice;
        this.type = type;
    }

    public String getId(){
        return id;
    }

    public String getParentBranch(){
        return parentBranch;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public VehicleTypes getType() {
        return type;
    }
}
