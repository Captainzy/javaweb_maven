package webservice.ws2.server;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Order")
public class Order {
	private String customerID;
    private String itemID;
    private int qty;
    private double price;
    
	public Order() {
		super();
	}
	public Order(String customerID, String itemID, int qty, double price) {
		super();
		this.customerID = customerID;
		this.itemID = itemID;
		this.qty = qty;
		this.price = price;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
    
}
