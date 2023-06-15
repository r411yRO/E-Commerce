package com.example.demo;

public class Payment {
	String paymentMethod;
	String orderStatus;
	int amountPaid;
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	@Override
	public String toString() {
		return "Payment [paymentMethod=" + paymentMethod + ", orderStatus=" + orderStatus + ", amountPaid=" + amountPaid
				+ "]";
	}
}
