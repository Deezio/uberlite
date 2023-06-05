package uberlite.dao;

import java.util.List;
import java.util.Optional;

import uberlite.model.Driver;
import uberlite.model.Ride;

public interface DriverDAO {
    void addDriver(Driver driver);
    Optional<Driver> getDriverByEmail(String email);
    List<Driver> getAllDrivers();
    void addRide(Driver driver, Ride ride);
}
