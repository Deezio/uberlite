package uberlite.model;

import uberlite.enums.PaymentMethod;
import uberlite.enums.PaymentStatus;

public class Payment {

	@Override
	public String toString() {
		return "Payment [fare=" + fare + ", paymentMethod=" + paymentMethod + ", status=" + status + "]";
	}
	private Double fare;
	private PaymentMethod paymentMethod;
	private PaymentStatus status;
	
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setPaymentStatus(PaymentStatus status) {
		this.status = status;
	}
}
