package DAL.impl;

import DAL.Repo;
import Entites.Vehicle;
import Entites.enums.VehicleTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleRepo implements Repo<Vehicle> {
    public static List<Vehicle> inMemDb = new ArrayList<>();

    @Override
    public Optional<Vehicle> get(String primaryKey) {
        return inMemDb.stream().filter(x-> x.getId().equals(primaryKey)).findAny();
    }

    @Override
    public void add(Vehicle entity) {
        inMemDb.add(entity);
    }

    public Integer getTotalVehicleCountByBranchAndType(String branchName, VehicleTypes typeEnum) {
        return inMemDb.stream()
                 .filter(x-> x.getParentBranch().equals(branchName))
                 .filter(x-> x.getType().equals(typeEnum))
                 .collect(Collectors.toList())
                 .size();
    }

    public Collection<? extends Vehicle> getVehilcesByBranchAndType(String branchName, VehicleTypes type) {
        return inMemDb.stream()
                .filter(x-> x.getParentBranch().equals(branchName))
                .filter(x-> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
