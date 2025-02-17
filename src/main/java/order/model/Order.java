package order.model;

import java.util.Date;

public class Order {
	
	int Order_ID, Order_Quantity,Cust_ID,Product_ID,Product_Quantity;
	Date Date_Order;
	double Total_price,Product_total_price;
	String Product_name, productids, productquantities;
	
	public int getOrder_ID() {
		return Order_ID;
	}
	public void setOrder_ID(int order_ID) {
		Order_ID = order_ID;
	}
	public int getOrder_Quantity() {
		return Order_Quantity;
	}
	public void setOrder_Quantity(int order_Quantity) {
		Order_Quantity = order_Quantity;
	}
	public int getCust_ID() {
		return Cust_ID;
	}
	public void setCust_ID(int cust_ID) {
		Cust_ID = cust_ID;
	}
	public int getProduct_ID() {
		return Product_ID;
	}
	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}
	public Date getDate_Order() {
		return Date_Order;
	}
	public void setDate_Order(Date date_Order) {
		Date_Order = date_Order;
	}
	public double getTotal_price() {
		return Total_price;
	}
	public void setTotal_price(double total_price) {
		Total_price = total_price;
	}
	public int getProduct_Quantity() {
		return Product_Quantity;
	}
	public void setProduct_Quantity(int product_Quantity) {
		Product_Quantity = product_Quantity;
	}
	public double getProduct_total_price() {
		return Product_total_price;
	}
	public void setProduct_total_price(double product_total_price) {
		Product_total_price = product_total_price;
	}
	public String getProduct_name() {
		return Product_name;
	}
	public void setProduct_name(String product_name) {
		Product_name = product_name;
	}
	public String getProductids() {
		return productids;
	}
	public void setProductids(String productids) {
		this.productids = productids;
	}
	public String getProductquantities() {
		return productquantities;
	}
	public void setProductquantities(String productquantities) {
		this.productquantities = productquantities;
	}
	
	

}
