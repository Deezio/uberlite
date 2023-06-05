package uberlite.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;
import uberlite.model.Driver;
import uberlite.model.Ride;

public class DriverDAOImpl implements DriverDAO {
    private List<Driver> drivers;
    private Map<Driver, List<Ride>> driverRides;


    public DriverDAOImpl() {
        this.drivers = new ArrayList<>();
        this.driverRides = new HashMap<>();
    }

    @Override
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    @Override
    public Optional<Driver> getDriverByEmail(String email) {
        return drivers.stream().filter(driver -> driver.getEmail().equals(email)).findFirst();
    }

    @Override
    public List<Driver> getAllDrivers() {
        return drivers;
    }
    
    @Override
    public void addRide(Driver driver, Ride ride) {
        List<Ride> rides = driverRides.getOrDefault(driver, new ArrayList<>());
        rides.add(ride);
        driverRides.put(driver, rides);
    }
}

