package product.model;

import java.io.InputStream;

public class Product {

	String product_name,product_category;
	int product_ID,product_stock;
	double product_price;
	String prodimg;	//for displaying image
	InputStream prodimgadd;	//for uploading image
	int staff_ID;
	
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public int getProduct_ID() {
		return product_ID;
	}
	public void setProduct_ID(int product_ID) {
		this.product_ID = product_ID;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public String getProdimg() {
		return prodimg;
	}
	public void setProdimg(String prodimg) {
		this.prodimg = prodimg;
	}
	public InputStream getProdimgadd() {
		return prodimgadd;
	}
	public void setProdimgadd(InputStream prodimgadd) {
		this.prodimgadd = prodimgadd;
	}
	public int getStaff_ID() {
		return staff_ID;
	}
	public void setStaff_ID(int staff_ID) {
		this.staff_ID = staff_ID;
	}
	
	public int CalculateStock(int product_stock, int quantity) {
		return (product_stock - quantity);
	}
	
	public int addStock(int product_stock, int quantity) {
		return (product_stock + quantity);
	}
	
	
	
	
}
