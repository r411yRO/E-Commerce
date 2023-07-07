
package com.example.demo;
import java.util.*;

public class Payment {
	private static List<String> acceptedMethods=Arrays.asList("VISA","PayPal","MasterCard","Maestro");
	private String paymentMethod;
	private String orderStatus;
	private int amountPaid;
	private Cart cart;
	private User user;
	public Payment(String paymentMethod,int amountPaidByUser) {
		this.amountPaid=amountPaidByUser;
		this.paymentMethod=paymentMethod;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void updateOrderStatus(String status) {
		this.orderStatus = status;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	@Override
	public String toString() {
		return generatePaymentReceipt();
	}
	public void processPayment() {
		if(this.amountPaid>cart.getTotalPrice() && acceptedMethods.contains(this.paymentMethod))
			updateOrderStatus("Transaction Accepted");
		else if(this.amountPaid<cart.getTotalPrice()) {
			updateOrderStatus("Transaction Declined - Insufficient Funds");
			calculateRemainingAmount();
		}
		else if(!acceptedMethods.contains(paymentMethod))
			updateOrderStatus("Transaction Declined - Payment Method Not Accepted");
		else
		{
			updateOrderStatus("Unnexpected error - Transaction cannot continue");
			refundPayment();
		}
	}
	private void refundPayment() {
		user.addBalance(this.amountPaid);
	}
	private double calculateRemainingAmount() {
		return cart.getTotalPrice()-this.amountPaid;
	}
	public String generatePaymentReceipt() {
		processPayment();
		String receipt=new String();
		receipt="The transaction of method type "+
				getPaymentMethod()+"of the following products:"
				+cart.listCartProducts()+"produts that ammounted to "
				+cart.getTotalPrice()+","
				+" ended with status:"+getOrderStatus();
		return receipt;
	}
}
