package Behaviours.impl;

import Behaviours.PricingStrategy;
import Common.Constants;
import Entites.enums.VehicleTypes;

public class SurgePricingStrategy implements PricingStrategy {
    @Override
    public Double getPriceFactor(VehicleTypes type,Integer totalVehiclesOfTypeAtBranch, Integer avalibleVehiclesOfTypeAtBranch) {
        if(!Constants.candidatesForSurge.contains(type)){
            return Constants.SURGE_BASE;
        }

        Double percentBooked = ((totalVehiclesOfTypeAtBranch-avalibleVehiclesOfTypeAtBranch)*100.0/totalVehiclesOfTypeAtBranch);
        if(percentBooked < Constants.SURGE_THRESHOLD){
            return Constants.SURGE_BASE;
        }

        return Constants.SURGE_PERCENT;
    }
}
