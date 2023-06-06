package uberlite.main;
import java.util.*;

import uberlite.model.*;
import uberlite.dao.*;
import uberlite.enums.PaymentMethod;
import uberlite.service.*;
public class Main {
    public static void main(String[] args) {
        List<Driver> drivers = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Ride ride = new Ride();
        Map<Integer,Ride> rides=new HashMap<>();
        Map<Integer,User> usesrs=new HashMap<>();
        
        int usercount=1;
        
        
        Random r=new Random();
        // Initialize the drivers and cars lists

        UberService service = new UberService(drivers, cars, users);
        UserDAO userDAO = new UserDAOImpl();
        service.setUserDAO(userDAO);
        DriverDAO driverDAO = new DriverDAOImpl();
        service.setDriverDAO(driverDAO);
        
        Validations validation=new Validations();

        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        while (true) {
            System.out.println("Welcome to Uber!");
            System.out.println("1. Request a ride");
            System.out.println("2. Complete a ride");
            System.out.println("3. Register as a user");
            System.out.println("4. Register as a driver");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    // Request a ride
                    System.out.println("Do you have a user id y/n");
                    if(!scanner.nextLine().equals("y")) {
                    	System.out.println("Please register as a user");
                    }else {
                    	System.out.println("enter your user Id");
                    	int id=scanner.nextInt();
                    	if(!usesrs.containsKey(id)) {
                    		System.out.println("invalid User ID");
                    		break;
                    	}
                    	
                    	User user=usesrs.get(id);
                    	if(user.getIsriding()==true) {
                    		System.out.println("complete your current ride ");
                    		break;
                    	}
                    System.out.print("Enter the starting longitude: ");
                    double startLongitude = scanner.nextDouble();

                    System.out.print("Enter the starting latitude: ");
                    double startLatitude = scanner.nextDouble();

                    Location startLocation = new Location();
                    startLocation.setLongitude(startLongitude);
                    startLocation.setLatitude(startLatitude);

                    System.out.print("Enter the ending longitude: ");
                    double endLongitude = scanner.nextDouble();

                    System.out.print("Enter the ending latitude: ");
                    double endLatitude = scanner.nextDouble();

                    Location endLocation = new Location();
                    endLocation.setLongitude(endLongitude);
                    endLocation.setLatitude(endLatitude);

                    System.out.println("Select a ride category:");
                    System.out.println("1. Economy");
                    System.out.println("2. Premium");
                    System.out.println("3. Pool");
                    System.out.print("Enter your choice: ");

                    int categoryChoice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    RideCategory rideCategory;
                    switch (categoryChoice) {
                        case 1:
                            rideCategory = new EconomyRide();
                            break;
                        case 2:
                            rideCategory = new PremiumRide();
                            break;
                        case 3:
                            rideCategory = new PoolRide();
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            continue;
                    }

                    System.out.println("Select a payment method:");
                    System.out.println("1. Cash");
                    System.out.println("2. Credit card");
                    System.out.println("3. Gpay");
                    System.out.print("Enter your choice: ");

                    int paymentChoice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    PaymentMethod paymentMethod;
                    switch (paymentChoice) {
                        case 1:
                            paymentMethod = PaymentMethod.CASH;
                            break;
                        case 2:
                            paymentMethod = PaymentMethod.CREDIT_CARD;
                        case 3:
                        	paymentMethod = PaymentMethod.GPAY;
                        	break;
                        	default:
                        	System.out.println("Invalid choice.");
                        	continue;
                        	}
                    System.out.println("Searching for a driver...");
                    Driver driver = service.findAvailableDriver(rideCategory.getName());
                    if (driver == null) {
                        System.out.println("No drivers available at the moment. Please try again later.");
                        continue;
                    }

                    System.out.println("Driver found! Assigning ride...");
                    
                    ride.setUser(user);
                    ride.setStartLocation(startLocation);
                    ride.setEndLocation(endLocation);
                    ride.setRideCategory(rideCategory);
                    ride.setPaymentMethod(paymentMethod);
                    ride.setDriver(driver);
                    
                    ride.setId(r.nextInt(1001));
                    rides.put(ride.getId(), ride);

                    service.requestRide(user, startLocation, endLocation, rideCategory, paymentMethod);
                    ride.getDriver().setAvailable(false);
                    ride.getUser().setIsriding(true);
                    
                    

                    System.out.println("Ride assigned successfully!");
                    System.out.println("Ride ID :-"+ride.getId());
                    System.out.println("Driver details:");
                    System.out.println("Name: " + driver.getName());
                    System.out.println("Phone number: " + driver.getPhoneNo());
                    System.out.println("Car details:");
                    System.out.println("Plate No: " + driver.getCar().getPlateNo());
                    System.out.println("Model: " + driver.getCar().getModel());
                    System.out.println("Capacity: " + driver.getCar().getCapacity());
                    System.out.println("Starting location: " + startLocation.toString());
                    System.out.println("Ending location: " + endLocation.toString());
                    System.out.println("Ride category: " + ride.getRideCategory().getName());
                    System.out.println("Ride fare: " +ride.getRideCategory().calculateFare(ride));
                    System.out.println("Payment method: " + ride.getPaymentMethod());
                    }
                    break;

                case 2:
                    // Complete a ride
                	
                	System.out.println("Enter your ride Id");
                	int id=scanner.nextInt();
                	if(rides.containsKey(id)) {
                	Ride finalride = rides.get(id);

                    service.completeRide(finalride);

                    System.out.println("Ride completed successfully!");
                    
                    rides.remove(id);
                    
                	}
                	else
                	System.out.println("Invalid ride Id ");
                    break;

                case 3:
                    // Register as a user
                	System.out.print("Enter your name: ");
                    String name = scanner.nextLine();

//                    System.out.print("Enter your phone number: ");
//                    String phoneNo = scanner.nextLine();
                    
                    String phoneNo=null;
                    boolean isValidNumber = false;

                    while (!isValidNumber) {
                    	
                    System.out.print("Enter your phone number: ");
                     String userPhone = scanner.nextLine();
                    isValidNumber = validation.validatePhoneNumber(userPhone);

                    if (isValidNumber) {
                    } else {
                        System.out.println("Invalid phone number format.");
                    }
                    
                    } 

                    System.out.print("Enter your email address: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setPhoneNo(phoneNo);
                    newUser.setEmail(email);
                    newUser.setPassword(password);

                    service.registerUser(newUser);
                    

                    System.out.println("User registered successfully with ID :: "+ usercount);
                    usesrs.put(usercount++, newUser);
                    break;

                case 4:
                    // Register as a driver
                	 System.out.print("Enter your name: ");
                     String driverName = scanner.nextLine();

//                     System.out.print("Enter your phone number: ");
//                     String driverPhone = scanner.nextLine();
                     
                     String driverPhone=null;
                     boolean isValidDriverNumber = false;

                     while (!isValidDriverNumber) {
                     	
                     System.out.print("Enter your phone number: ");
                     driverPhone = scanner.nextLine();
                     isValidDriverNumber = validation.validatePhoneNumber(driverPhone);

                     if (isValidDriverNumber) {
                         
                     } else {
                         System.out.println("Invalid phone number format.");
                     }
                     
                     } 

                     
                     String driverEmail=null;
                     boolean isValidDriverEmail = false;

                     while (!isValidDriverEmail) {
                     	 System.out.print("Enter your email address: ");
                          driverEmail = scanner.nextLine();
                         
                          isValidDriverEmail = validation.validateEmail(driverEmail);

                         if (isValidDriverEmail) {
                         } else {
                             System.out.println("Invalid email address format.");
                         }
                     }
                    

                     System.out.print("Enter your password: ");
                     String driverPassword = scanner.nextLine();

                     System.out.print("Enter your car model: ");
                     String carModel = scanner.nextLine();

                     
//                     System.out.print("Enter your car license plate: ");
//                     String carLicensePlate = scanner.nextLine();
                     String carLicensePlate=null;
                     boolean isValidLicensePlate = false;

                     while (!isValidLicensePlate) {
                         System.out.print("Enter your car license plate: ");
                         carLicensePlate = scanner.nextLine();
                         
                         // Perform license plate validation
                         isValidLicensePlate = validation.validateLicensePlate(carLicensePlate);

                         if (isValidLicensePlate) {
                         } else {
                             System.out.println("Invalid license plate format. Please try again.");
                         }
                     }
                    System.out.print("Enter your car cpacity: ");
                    Integer capacity = scanner.nextInt();
                    

                    Driver newDriver = new Driver();
                    newDriver.setName(driverName);
                    newDriver.setPhoneNo(driverPhone);
                    newDriver.setEmail(driverEmail);
                    newDriver.setPassword(driverPassword);

                    Car newCar = new Car();
                    newCar.setPlateNo(carLicensePlate);
                    newCar.setModel(carModel);
                    newCar.setCapacity(capacity);
                    
                    newDriver.setCar(newCar);
                    List<RideCategory> cat=new ArrayList<>();
                    cat.add(new EconomyRide());
                    cat.add(new PoolRide());
                    cat.add(new PremiumRide());
                    newDriver.setAvailableCategories(cat);

                    service.registerDriver(newDriver);
                    
                    

                    System.out.println("Driver registered successfully!");
                    break;

                case 5:
                    // Exit the program
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

