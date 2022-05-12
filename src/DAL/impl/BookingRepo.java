package DAL.impl;

import DAL.Repo;
import Entites.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingRepo implements Repo<Booking> {
    public static List<Booking> inMemDb = new ArrayList<>();
    @Override
    public Optional<Booking> get(String primaryKey) {
        return inMemDb.stream().filter(x-> x.getId().equals(primaryKey)).findAny();
    }

    @Override
    public void add(Booking entity) {
        inMemDb.add(entity);
    }

    public void updateBookingAsClosed(String vehicleId) {
        Optional<Booking> booking = inMemDb.stream().filter(x -> x.getVehicleId().equals(vehicleId)).findFirst();
        if(!booking.isPresent()){
            return;
        }
        booking.get().setClosed(true);
    }

    public List<Booking> getActiveBookingsInRange(String branchName, Integer startHour, Integer endHour) {
        return inMemDb
                .stream()
                .filter(x-> x.getBranchId().equals(branchName))
                //.filter(x-> x.getStartHour()>=startHour)
                //.filter(x-> x.getStartHour()<=endHour)
                .filter(x-> !x.isClosed())
                .collect(Collectors.toList());
    }
}
