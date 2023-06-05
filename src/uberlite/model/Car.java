package uberlite.model;

public class Car {

	private String model;
	private String plateNo;
	private Integer capacity;
	private boolean isAvailable;


	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", plateNo=" + plateNo + ", capacity=" + capacity + ", isAvailable="
				+ isAvailable + "]";
	}

}
