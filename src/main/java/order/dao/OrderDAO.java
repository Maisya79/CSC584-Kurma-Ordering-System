package order.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import cart.model.Cart;
import db.connection.ConnectionManager;
import order.model.Order;
import product.model.Product;

public class OrderDAO {
	static Connection con=null;
	static Statement stmt = null;
	static PreparedStatement ps=null;
	
	String product_name,product_category;
	int product_ID,product_quantity,cust_ID;
	double product_total_price,order_total_price;
	 
		//add order
			public void addOrder(Order ord) {
				//get order
				cust_ID = ord.getCust_ID();
				order_total_price = ord.getTotal_price();
				
				//query to database
				try {
				// 2. create the connection object 1. at connectionmanager
				con = ConnectionManager.getConnection();
				
				//3. create the statement object
				ps=con.prepareStatement("insert into orderkurma (Date_Order,Total_Price,Cust_ID)values(?,?,?)");
				java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
				ps.setDate(1, sqlDate);
				ps.setDouble(2, order_total_price);
				ps.setInt(3, cust_ID);
				
				//4. execute query
				ps.executeUpdate();
				
				//5. close the connection object
				con.close();
				
				}catch(Exception ex) {
					System.out.println(ex);
				}
			}
		
		//list current order details
		 public static List<Order> getCurrentOrder() throws IOException { 
			  List<Order> ords = new ArrayList<Order>(); 
			  try { 
				  con = ConnectionManager.getConnection();
				  stmt = con.createStatement(); 
				  ResultSet rs = stmt.executeQuery("select o.Order_ID, o.Date_Order, o.Total_Price, o.Cust_ID, c.Product_ID, p.Product_name, c.Product_Quantity, c.Product_total_price from orderkurma AS o INNER JOIN cart AS c INNER JOIN product AS p where c.Product_ID = p.Product_ID AND c.Order_ID = o.Order_ID AND o.Order_ID =(SELECT MAX(Order_ID) AS Order_ID  FROM orderkurma)  GROUP BY c.Product_ID");
		  
				  while (rs.next()) { 
	
					  Order ord = new Order();
					  ord.setOrder_ID(rs.getInt("Order_ID"));
					  ord.setDate_Order(rs.getDate("Date_Order"));
					  ord.setTotal_price(rs.getDouble("Total_Price"));
					  ord.setCust_ID(rs.getInt("Cust_ID"));
					  ord.setProduct_ID(rs.getInt("Product_ID"));
					  ord.setProduct_name(rs.getString("Product_name"));
					  ord.setProduct_Quantity(rs.getInt("Product_Quantity"));
					  ord.setProduct_total_price(rs.getDouble("Product_total_price"));
					  ords.add(ord);
		  
				  } 
			  } catch (SQLException e) { 
				  e.printStackTrace(); 
			}
			  return ords;
		 }
	 
	 
	//list all order
		 public static List<Order> getAllOrder() throws IOException { 
			  List<Order> ords = new ArrayList<Order>(); 
			  try { 
				  con = ConnectionManager.getConnection();
				  stmt = con.createStatement(); 
				  ResultSet rs = stmt.executeQuery("select o.Order_ID, o.Date_Order, o.Total_Price, o.Cust_ID, GROUP_CONCAT(c.Product_ID), GROUP_CONCAT(p.Product_name), GROUP_CONCAT(c.Product_Quantity), c.Product_total_price from orderkurma AS o INNER JOIN cart AS c INNER JOIN product AS p where c.Product_ID = p.Product_ID AND c.Order_ID = o.Order_ID GROUP BY o.Order_ID");
		  
				  while (rs.next()) { 
	
					  Order ord = new Order();
					  ord.setOrder_ID(rs.getInt("Order_ID"));
					  ord.setDate_Order(rs.getDate("Date_Order"));
					  ord.setTotal_price(rs.getDouble("Total_Price"));
					  ord.setCust_ID(rs.getInt("Cust_ID"));
					  ord.setProductids(rs.getString("GROUP_CONCAT(c.Product_ID)"));
					  ord.setProduct_name(rs.getString("GROUP_CONCAT(p.Product_name)"));
					  ord.setProductquantities(rs.getString("GROUP_CONCAT(c.Product_Quantity)"));
					  ord.setProduct_total_price(rs.getDouble("Product_total_price"));
					  ords.add(ord);
		  
				  } 
			  } catch (SQLException e) { 
				  e.printStackTrace(); 
			}
			  return ords;
		 }
		 /*
		//view product
		 public static Product getProductById(int Product_ID) throws IOException {
			 Product prod = new Product();
			    try {
			    	 //2. create the connection object 			    	
			    	con = ConnectionManager.getConnection();
			        
			    	//3. create the statement object 
			    	ps=con.prepareStatement("select * from product where Product_ID=?");		        
			        ps.setInt(1, Product_ID);
			        
			        //4. execute query 		        
			        ResultSet rs = ps.executeQuery();

			        if (rs.next()) {
			        	
			        	//for getting image. Credit: Nam Ha Minh at codejava.net
						  Blob prodimg = rs.getBlob("prodimg");
						  InputStream inputStream = prodimg.getBinaryStream();
			              ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			              byte[] buffer = new byte[4096];
			              int bytesRead = -1;
			                 
			                while ((bytesRead = inputStream.read(buffer)) != -1) {
			                    outputStream.write(buffer, 0, bytesRead);                  
			                }
			                 
			                byte[] imageBytes = outputStream.toByteArray();
			                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			                 
			                 
			                inputStream.close();
			                outputStream.close();
			        	
			        	 prod.setProduct_ID(rs.getInt("Product_ID"));
						  prod.setProduct_name(rs.getString("Product_name"));
						  prod.setProduct_price(rs.getDouble("Product_price"));
						  prod.setProduct_stock(rs.getInt("Product_stock"));
						  prod.setProduct_category(rs.getString("Product_category"));
						  prod.setProdimg(base64Image);
						  prod.setStaff_ID(rs.getInt("Staff_ID"));
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }

			    return prod;
			}
		 //update product
		 public void update(Product bean) {
			 
			 int pid = bean.getProduct_ID();
			 String pname = bean.getProduct_name();
			 double pprice = bean.getProduct_price();
			 int pstock = bean.getProduct_stock();
			 String pcategory = bean.getProduct_category();
			 InputStream prodimg = bean.getProdimgadd();
			 int staffid = bean.getStaff_ID();
			 
			 try {
			 con = ConnectionManager.getConnection();
			 ps=con.prepareStatement("update product set Product_name=?, Product_price=?, Product_stock=?, Product_category=?, prodimg=?, Staff_ID=? where Product_ID=?");	
			 	
			 	ps.setString(1, pname);
				ps.setDouble(2, pprice);
				ps.setInt(3, pstock);
				ps.setString(4, pcategory);
				
				// Fetching image and store in database. Credit: Ramesh Fadatare
				if (prodimg != null) {
		            // fetches input stream of the upload file for the blob column
		            ps.setBinaryStream(5, prodimg,10000000);
		        }
				
				ps.setInt(6, staffid);
				ps.setInt(7, pid);
				
				//4. execute query
				ps.executeUpdate();
				
				//5. close the connection object
				con.close();
				
				}catch(Exception ex) {
					System.out.println(ex);
				}
				
			}
		 
		 //delete product
		 public void deleteProduct(int Product_ID) {
			    try {
			    	//2. create the connection object
			    	con = ConnectionManager.getConnection();
			    	
			    	//3. create the statement object
			    	ps= con.prepareStatement("delete from product where Product_ID=?");
			        ps.setInt(1, Product_ID);
			        
			        //4. execute query
			        ps.executeUpdate();

			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
		 
		//delete order for deleted customer
		 /*
		 public void deleteCustOrder(int Cust_ID) {
			    try {
			    	//2. create the connection object
			    	con = ConnectionManager.getConnection();
			    	
			    	//3. create the statement object
			    	ps= con.prepareStatement("delete o from order o INNER JOIN customer cu ON o.Cust_ID=cu.Cust_ID where Cust_ID=?");
			        ps.setInt(1, Cust_ID);
			        
			        //4. execute query
			        ps.executeUpdate();

			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
			*/

}
