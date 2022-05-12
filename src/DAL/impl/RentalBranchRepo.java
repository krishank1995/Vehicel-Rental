package DAL.impl;

import DAL.Repo;
import Entites.RentalBranch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalBranchRepo implements Repo<RentalBranch> {
    public static List<RentalBranch> inMemDb = new ArrayList<>();

    @Override
    public Optional<RentalBranch> get(String primaryKey) {
       return inMemDb.stream().filter(x-> x.getBranchName().equals(primaryKey)).findAny();
    }

    @Override
    public void add(RentalBranch entity) {
        inMemDb.add(entity);
    }
}
