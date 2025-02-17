package cart.dao;

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
import product.model.Product;

public class CartDAO {
	static Connection con=null;
	static Statement stmt = null;
	static PreparedStatement ps=null;
	
	String product_name,product_category;
	int product_ID,product_quantity,cust_ID,cart_ID;
	double product_total_price,order_total_price;
	
	//add to cart
		public void addtoCart(Cart cart) {
			//get product
			cust_ID = cart.getCust_ID();
			product_ID = cart.getProduct_ID();
			product_quantity = cart.getProduct_Quantity();
			product_total_price = cart.getProduct_total_price();
			
			//query to database
			try {
			// 2. create the connection object 1. at connectionmanager
			con = ConnectionManager.getConnection();
			
			//3. create the statement object
			ps=con.prepareStatement("insert into cart (Product_ID,Product_quantity,Product_total_price,Cust_ID)values(?,?,?,?)");
			ps.setInt(1, product_ID);
			ps.setInt(2, product_quantity);
			ps.setDouble(3, product_total_price);
			ps.setInt(4, cust_ID);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close the connection object
			con.close();
			
			}catch(Exception ex) {
				System.out.println(ex);
			}
		}
		
		//list all cart
		 public static List<Cart> getAllCart() throws IOException { 
			  List<Cart> carts = new ArrayList<Cart>(); 
			  try { 
				  con = ConnectionManager.getConnection();
				  stmt = con.createStatement(); 
				  ResultSet rs = stmt.executeQuery("select c.Cart_ID, c.Product_ID, p.Product_name, c.Product_Quantity, c.Product_total_price from cart AS c INNER JOIN product AS p where c.Product_ID = p.Product_ID AND c.Order_ID IS NULL");
		  
				  while (rs.next()) { 
					  	  
					  Cart cart = new Cart();
					  cart.setCart_ID(rs.getInt("Cart_ID"));
					  cart.setProduct_ID(rs.getInt("Product_ID"));
					  cart.setProduct_name(rs.getString("Product_name"));
					  cart.setProduct_Quantity(rs.getInt("Product_Quantity"));
					  cart.setProduct_total_price(rs.getDouble("Product_total_price"));
					  carts.add(cart);
		  
				  } 
			  } catch (SQLException e) { 
				  e.printStackTrace(); 
			}
			  return carts;
		 }
		 
		//view cart item
		 public static Cart getCartById(int Cart_ID) throws IOException {
			 Cart cart = new Cart();
			    try {
			    	 //2. create the connection object 			    	
			    	con = ConnectionManager.getConnection();
			        
			    	//3. create the statement object 
			    	ps=con.prepareStatement("select c.Cart_ID, c.Product_ID, p.Product_name, c.Product_Quantity, c.Product_total_price from cart AS c INNER JOIN product AS p where c.Product_ID = p.Product_ID AND c.Cart_ID=?");		        
			        ps.setInt(1, Cart_ID);
			        
			        //4. execute query 		        
			        ResultSet rs = ps.executeQuery();

			        while (rs.next()) {
			        	  cart.setProduct_ID(rs.getInt("Cart_ID"));
			        	  cart.setProduct_ID(rs.getInt("Product_ID"));
						  cart.setProduct_name(rs.getString("Product_name"));
						  cart.setProduct_total_price(rs.getDouble("Product_total_price"));
						  cart.setProduct_Quantity(rs.getInt("Product_Quantity"));
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }

			    return cart;
			}
		 
		//calculate total cart price
			 public static double calculateOrderTotal() {
				 double ordertotalprice = 0.0;
				    try {
				    	 //2. create the connection object 			    	
				    	con = ConnectionManager.getConnection();
				    	stmt = con.createStatement();
				    	//3. create the statement object 
				    	ResultSet rs = stmt.executeQuery("SELECT SUM(Product_total_price) FROM cart WHERE cart.Order_ID IS NULL");		        
				        
				        while (rs.next()) {	
				        	  
				        	ordertotalprice = rs.getDouble(1);
				        	
				        	  }

				    } catch (SQLException e) {
				        e.printStackTrace();
				    }
				    return ordertotalprice;
				}
			 
			 
			 
				//add orderid to cart
					public void addOrderID() {
						//query to database
						try {
						// 2. create the connection object 1. at connectionmanager
						con = ConnectionManager.getConnection();
						
						//3. create the statement object
						ps=con.prepareStatement("UPDATE cart AS c CROSS JOIN (SELECT MAX(Order_ID) AS Order_ID  FROM orderkurma) AS o SET c.Order_ID = o.Order_ID WHERE c.Order_ID IS NULL");
					
						//4. execute query
						ps.executeUpdate();
						
						//5. close the connection object
						con.close();
						
						}catch(Exception ex) {
							System.out.println(ex);
						}
					}
					
					//count no of products ordered by user
					 public static List<Cart> getProductSales() throws IOException { 
						 List<Cart> carts = new ArrayList<Cart>(); 
						  try { 
							  con = ConnectionManager.getConnection();
							  stmt = con.createStatement(); 
							  ResultSet rs = stmt.executeQuery("SELECT c.Product_ID,p.Product_name,SUM(c.Product_Quantity) AS totalsale FROM cart c inner Join product p on p.Product_ID = c.Product_ID GROUP BY c.Product_ID ORDER BY totalsale DESC");
					  
							  while (rs.next()) { 
								  Cart cart = new Cart();
								  cart.setProduct_ID(rs.getInt("Product_ID"));
								  cart.setProduct_name(rs.getString("Product_name"));
								  cart.setProduct_Quantity(rs.getInt("totalsale"));
								  carts.add(cart);
					  
							  } 
						  } catch (SQLException e) { 
							  e.printStackTrace(); 
						}
						  return carts;
					 }
					 
					 /*
					//get product price
					 public static double getProductprice(int Cart_ID) {
						 double prodprice=0.0;
						// int cid = bean.getCart_ID();
						 
						 try {
							 con = ConnectionManager.getConnection();
							  stmt = con.createStatement(); 
							  ResultSet rs = stmt.executeQuery("select p.Product_price from cart AS c INNER JOIN product AS p where c.Cart_ID='" + Cart_ID + "'AND p.Product_ID=c.Product_ID");		        
					       
					        while (rs.next()) {
					        	prodprice = rs.getDouble(1);
					        } 
						  } catch (SQLException e) { 
							  e.printStackTrace(); 
						}
						  return prodprice;
					 }
					 
					//update cart
						public void updateCart(Cart cart) {
							//get product
							product_name = cart.getProduct_name();
							cart_ID = cart.getCart_ID();
							product_quantity = cart.getProduct_Quantity();
							product_total_price = cart.getProduct_total_price();
							
							//query to database
							try {
							// 2. create the connection object 1. at connectionmanager
							con = ConnectionManager.getConnection();
							
							//3. create the statement object
							ps=con.prepareStatement("update cart set Product_Quantity=?,Product_total_price=? where Cart_ID=?");
							ps.setInt(1, product_quantity);
							ps.setDouble(2, product_total_price);
							ps.setInt(3, cart_ID);
						//	ps.setInt(4, product_ID);
							
							//4. execute query
							ps.executeUpdate();
							
							//5. close the connection object
							con.close();
							
							}catch(Exception ex) {
								System.out.println(ex);
							}
						}
						*/
					 
					//delete cart item
					 public void deleteCart(int Cart_ID) {
						    try {
						    	//2. create the connection object
						    	con = ConnectionManager.getConnection();
						    	
						    	//3. create the statement object
						    	ps= con.prepareStatement("delete from cart where Cart_ID=?");
						        ps.setInt(1, Cart_ID);
						        
						        //4. execute query
						        ps.executeUpdate();

						    } catch (SQLException e) {
						        e.printStackTrace();
						    }
						}
					 
					//delete cart item for deleted customer
					 /*
					 public void deleteCustCart(int Cust_ID) {
						    try {
						    	//2. create the connection object
						    	con = ConnectionManager.getConnection();
						    	
						    	//3. create the statement object
						    	ps= con.prepareStatement("delete c from cart c INNER JOIN orderkurma o ON c.Order_ID=o.Order_ID where Cust_ID=?");
						        ps.setInt(1, Cust_ID);
						        
						        //4. execute query
						        ps.executeUpdate();

						    } catch (SQLException e) {
						        e.printStackTrace();
						    }
						}
						*/

}
