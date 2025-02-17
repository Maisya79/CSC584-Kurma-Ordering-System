package cart.model;


public class Cart {
	
	int Cart_ID, Product_Quantity,Product_ID,Product_in_cart_ID,Cust_ID;
	String Product_name;
	double Product_total_price;
	
	public int getCart_ID() {
		return Cart_ID;
	}
	public void setCart_ID(int cart_ID) {
		Cart_ID = cart_ID;
	}
	public int getProduct_Quantity() {
		return Product_Quantity;
	}
	public void setProduct_Quantity(int product_Quantity) {
		Product_Quantity = product_Quantity;
	}
	public int getProduct_ID() {
		return Product_ID;
	}
	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
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
	public int getProduct_in_cart_ID() {
		return Product_in_cart_ID;
	}
	public void setProduct_in_cart_ID(int product_in_cart_ID) {
		Product_in_cart_ID = product_in_cart_ID;
	}
	
	public double Calculateprice(int quantity, double productprice) {
		return (quantity * productprice);
		
	}
	public int getCust_ID() {
		return Cust_ID;
	}
	public void setCust_ID(int cust_ID) {
		Cust_ID = cust_ID;
	}
	
	

}
