package Entites;

public class Booking {
    private String id;

    private String branchId;
    private String vehicleId;

    private Integer startHour;
    private Integer endHour;

    private Double bookedPrice;

    private boolean closed;

    public Booking(String id,String branchId,String vehicleId,Integer startHour,Integer endHour,Double bookedPrice){
        this.id = id;
        this.bookedPrice = bookedPrice;
        this.startHour = startHour;
        this.endHour = endHour;
        this.branchId = branchId;
        this.vehicleId = vehicleId;
    }

    public String getId() {
        return id;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public Integer getEndHour(){
        return endHour;
    }
}
