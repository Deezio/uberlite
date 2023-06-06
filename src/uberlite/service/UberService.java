package uberlite.service;
import java.util.*;

import uberlite.model.*;
import uberlite.dao.*;
import uberlite.enums.PaymentMethod;
import uberlite.enums.PaymentStatus;
public class UberService {
    private List<Ride> rides;
    private List<User> users;
    private List<Driver> drivers;
    private List<Car> cars;
    private UserDAO userDAO;
    private DriverDAO driverDAO;

//    UserDAO userDAO = new UserDAO();
//    DriverDAO driverDAO = new DriverDAO();
//    UberService service = new UberService(drivers, cars, userDAO, driverDAO);

    public UberService(List<Driver> drivers, List<Car> cars, List<User> users) {
        this.rides = new ArrayList<>();
        this.users = users;
        this.drivers = drivers;
        this.cars = cars;
    }
    
    public void setDriverDAO(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }
    
    public void setUserDAO(UserDAO userDAO) {
    	this.userDAO = userDAO;
    }
    public void requestRide(User user, Location startLocation, Location endLocation, RideCategory rideCategory, PaymentMethod paymentMethod) {
        // Find an available driver and car that match the ride category
        Driver driver = findAvailableDriver(rideCategory.getName());
        Car car = findAvailableCar(rideCategory);
        
        // Create a new ride with the selected driver, car, and ride category
        Ride ride = new Ride();
        ride.setUser(user);
        ride.setDriver(driver);
        ride.setStartLocation(startLocation);
        ride.setEndLocation(endLocation);
        ride.setRideCategory(rideCategory);
        
        // Calculate fare based on the selected ride category
        double fare = rideCategory.calculateFare(ride);
        
        // Set payment details for the ride
        Payment payment = new Payment();
        payment.setFare(fare);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        ride.setPayment(payment);
        
        // Add the new ride to the list of rides
        rides.add(ride);
    }
    public Driver findAvailableDriver(String rideCategory) {
        // Find a driver that matches the given ride category and is available
    	
        for (Driver driver : drivers) {
        	if(driver.isAvailable())
        	for(RideCategory r:driver.getAvailableCategories()) {
        		if(r.getName().equals(rideCategory)) {
        			return driver;
        		}
        	}
        }
        return null;
    }

    private Car findAvailableCar(RideCategory rideCategory) {
        // Find a car that matches the given ride category and is available
        for (Car car : cars) {
            if (car.getCapacity() >= rideCategory.getCapacity() && car.isAvailable()) {
                return car;
            }
        }
        return null;
    }

    public void completeRide(Ride ride) {
        // Mark the ride as completed and update the payment status
        ride.setCompleted(true);
        
        // Update the driver's and car's availability
        ride.getDriver().setAvailable(true);
        ride.getUser().setIsriding(false);
        
        
        // Update the user and driver records with the completed ride
        userDAO.addRide(ride.getUser(), ride);
        driverDAO.addRide(ride.getDriver(), ride);
        }
    
    public List<Ride> searchRidesByUser(User user) {
        // Find all rides requested by the given user
        List<Ride> userRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getUser().equals(user)) {
            	userRides.add(ride);
            }
        }
        return userRides;
    }

    public List<Ride> searchRidesByDriver(Driver driver) {
        // Find all rides completed by the given driver
        List<Ride> driverRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getDriver().equals(driver) && ride.isCompleted()) {
                driverRides.add(ride);
            }
        }
        return driverRides;
    }

    public void registerUser(User user) {
        // Add the new user to the database (in this case, just the list of users)
        users.add(user);
    }

    public void registerDriver(Driver driver) {
        // Add the new driver to the database (in this case, just the list of drivers)
        drivers.add(driver);
    }

//    public void payForRide(Ride ride) {
//        // Get the payment details for the ride
//        Payment payment = ride.getPayment();
//    }
 } 