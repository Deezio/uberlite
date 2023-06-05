package uberlite.model;

import uberlite.dao.RideCategory;
import uberlite.enums.PaymentMethod;

public class Ride {
	@Override
	public String toString() {
		return "Ride [user=" + user + ", driver=" + driver + ", car=" + car + ", completed=" + completed
				+ ", startLocation=" + startLocation + ", endLocation=" + endLocation + ", payment=" + payment
				+ ", paymentMethod=" + paymentMethod + ", rideCategory=" + rideCategory + "]";
	}
	
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	private User user;
	private Driver driver;
	private Car car;
	private boolean completed;
	private Location startLocation;
	private Location endLocation;
	private Payment payment;
	private PaymentMethod paymentMethod;
	private RideCategory rideCategory;
	

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Location getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}
	public Location getDestination() {
		return endLocation;
	}
	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public RideCategory getRideCategory() {
		return rideCategory;
	}
	public void setRideCategory(RideCategory rideCategory) {
		this.rideCategory = rideCategory;
	}

}
