package Service.impl;

import Behaviours.PricingStrategy;
import Behaviours.impl.SurgePricingStrategy;
import Common.Constants;
import DAL.impl.BookingRepo;
import DAL.impl.RentalBranchRepo;
import DAL.impl.VehicleRepo;
import Entites.Booking;
import Entites.Vehicle;
import Entites.enums.VehicleTypes;
import Service.BookingService;

import java.util.*;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private BookingRepo bookingRepo;
    private VehicleRepo vehicleRepo;
    private RentalBranchRepo branchRepo;
    private PricingStrategy pricingStrategy;

    public BookingServiceImpl(){
        bookingRepo = new BookingRepo();
        vehicleRepo = new VehicleRepo();
        branchRepo = new RentalBranchRepo();
        pricingStrategy = new SurgePricingStrategy();
    }


    public Double bookVehicle(String branchName,String vehicleType,Integer startHour,Integer endHour){
        VehicleTypes typeEnum = null;
        //Vehicle Type Validity Check
        try{
            typeEnum = VehicleTypes.valueOf(vehicleType);
        } catch(IllegalArgumentException ex){
            System.out.println(String.format(Constants.WRONG_VEHICLE_TYPE));
            return -1D;
        }

        List<Vehicle> availableVehicles = getAvailableVehicles(branchName, startHour, endHour, Arrays.asList(typeEnum));

        if(availableVehicles.size()==0){
            return -1D;
        }

        Double priceFactor = pricingStrategy.getPriceFactor(typeEnum, vehicleRepo.getTotalVehicleCountByBranchAndType(branchName, typeEnum), availableVehicles.size());
        Vehicle vehicleBeingBooked = availableVehicles.stream().findFirst().get();
        Double bookedPrice = priceFactor*vehicleBeingBooked.getRentalPrice();

        bookingRepo.add( new Booking(UUID.randomUUID().toString(),branchName,vehicleBeingBooked.getId(),startHour,endHour,bookedPrice));
        return  bookedPrice;
    }

    public List<String> displayAvailableVehicleIds(String branchName, Integer startHour, Integer endHour){
        return getAvailableVehicles(branchName,startHour,endHour,Arrays.asList(VehicleTypes.values())).stream().map(x->x.getId()).collect(Collectors.toList());
    }

    public boolean closeBooking(String vehicleId){
        bookingRepo.updateBookingAsClosed(vehicleId);
        return true;
    }

    private List<Vehicle> getAvailableVehicles(String branchName, Integer startHour, Integer endHour,List<VehicleTypes> vehicleTypes){
        List<Vehicle> candidates = new ArrayList<>();
        vehicleTypes.stream().forEach(type -> {
            candidates.addAll(vehicleRepo.getVehilcesByBranchAndType(branchName,type));
        });

        List<Booking> bookingsInTimeRange = bookingRepo.getActiveBookingsInRange(branchName,startHour,endHour);
        Set<String> bookedVehicles = bookingsInTimeRange.stream().map(x->x.getVehicleId()).collect(Collectors.toSet());

        List<Vehicle> finalCandidates = new ArrayList<>();
        for(Vehicle candidate: candidates){
            if(!bookedVehicles.contains(candidate.getId())){
                finalCandidates.add(candidate);
            }
        }

        Collections.sort(finalCandidates, Comparator.comparing(Vehicle::getRentalPrice));

        return finalCandidates;
    }
}
