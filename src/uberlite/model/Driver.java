package uberlite.model;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import uberlite.dao.RideCategory;

public class Driver {

	private String name;
    private String phoneNo;
    private String email;
    private String password;
    private String license;
    private boolean isAvailable=true;
    private Car car;
    private List<RideCategory> availableCategories;


    public Driver() {
        availableCategories = new ArrayList<>();
    }
    
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}
    

	public void setAvailableCategories(List<RideCategory> categories) {
        this.availableCategories = categories;
    }

    public List<RideCategory> getAvailableCategories() {
        return availableCategories;
    }
    
	@Override
	public int hashCode() {
		return Objects.hash(availableCategories, car, email, isAvailable, license, name, password, phoneNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(availableCategories, other.availableCategories) && Objects.equals(car, other.car)
				&& Objects.equals(email, other.email) && isAvailable == other.isAvailable
				&& Objects.equals(license, other.license) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(phoneNo, other.phoneNo);
	}

	public String getName() {
		return name;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	@Override
	public String toString() {
		return "Driver [name=" + name + ", phoneNo=" + phoneNo + ", email=" + email + ", password=" + password
				+ ", license=" + license + ", isAvailable=" + isAvailable + ", car=" + car + ", availableCategories="
				+ availableCategories + "]";
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
