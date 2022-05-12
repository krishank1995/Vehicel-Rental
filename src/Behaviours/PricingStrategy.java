package Behaviours;

import Entites.enums.VehicleTypes;

public interface PricingStrategy {

    Double getPriceFactor(VehicleTypes type,Integer totalVehiclesOfType, Integer avalibleVehiclesOfType);
}
