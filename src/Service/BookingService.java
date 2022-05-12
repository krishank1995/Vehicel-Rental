package Service;

import java.util.List;

public interface BookingService {
    Double bookVehicle(String branchName,String vehicleType,Integer startHour,Integer endHour);
    List<String> displayAvailableVehicleIds(String branchName, Integer startHour, Integer endHour);
     boolean closeBooking(String vehicleId);

}
