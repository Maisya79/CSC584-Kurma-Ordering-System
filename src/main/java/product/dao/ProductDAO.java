package product.dao;

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

import db.connection.ConnectionManager;
import product.model.Product;

public class ProductDAO {
	static Connection con=null;
	static Statement stmt = null;
	static PreparedStatement ps=null;
	
	String product_name,product_category;
	int product_ID,product_stock,staff_ID;
	double product_price;
	InputStream prodimg;
	
	//add product
	public void add(Product bean) {
		//get product
		product_name = bean.getProduct_name();
		product_price = bean.getProduct_price();
		product_stock = bean.getProduct_stock();
		product_category = bean.getProduct_category();
		prodimg = bean.getProdimgadd();
		//staff_ID = bean.getStaff_ID();
		
		//query to database
		try {
		// 2. create the connection object 1. at connectionmanager
		con = ConnectionManager.getConnection();
		
		//3. create the statement object
		ps=con.prepareStatement("insert into product (Product_name,Product_price,Product_stock,Product_category,prodimg)values(?,?,?,?,?)");
		ps.setString(1, product_name);
		ps.setDouble(2, product_price);
		ps.setInt(3, product_stock);
		ps.setString(4, product_category);
		
		// Fetching image and store in database. Credit: Ramesh Fadatare
		if (prodimg != null) {
            // fetches input stream of the upload file for the blob column
            ps.setBinaryStream(5, prodimg,10000000);
        }
		
		
		//4. execute query
		ps.executeUpdate();
		
		//5. close the connection object
		con.close();
		
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	//get recently added product
	 public static Product getRecentlyadded() throws IOException {
			Product prod = new Product();
		    try {
		    	 //2. create the connection object 			    	
		    	con = ConnectionManager.getConnection();
		    	//3. create the statement object 
		        stmt = con.createStatement();
		        //4. execute query 
		    	ResultSet rs = stmt.executeQuery("select * from product where Product_ID = (SELECT MAX(Product_ID))");		   //selectmax     
		        
		        while (rs.next()) {	   
		        	
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
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return prod;
		}
	 
	//list all product
		 public static List<Product> getAllProduct() throws IOException { 
			  List<Product> prods = new ArrayList<Product>(); 
			  try { 
				  con = ConnectionManager.getConnection();
				  stmt = con.createStatement(); 
				  ResultSet rs = stmt.executeQuery("select * from product order by Product_ID");
		  
				  while (rs.next()) { 
					  
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
					  
					  Product prod = new Product();
					  prod.setProduct_ID(rs.getInt("Product_ID"));
					  prod.setProduct_name(rs.getString("Product_name"));
					  prod.setProduct_price(rs.getDouble("Product_price"));
					  prod.setProduct_stock(rs.getInt("Product_stock"));
					  prod.setProduct_category(rs.getString("Product_category"));
					  prod.setProdimg(base64Image);
					  prods.add(prod);
		  
				  } 
			  } catch (SQLException e) { 
				  e.printStackTrace(); 
			}
			  return prods;
		 }
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
			
			 
			 try {
			 con = ConnectionManager.getConnection();
			 ps=con.prepareStatement("update product set Product_name=?, Product_price=?, Product_stock=?, Product_category=?, prodimg=? where Product_ID=?");	
			 	
			 	ps.setString(1, pname);
				ps.setDouble(2, pprice);
				ps.setInt(3, pstock);
				ps.setString(4, pcategory);
				
				// Fetching image and store in database. Credit: Ramesh Fadatare
				if (prodimg != null) {
		            // fetches input stream of the upload file for the blob column
		            ps.setBinaryStream(5, prodimg,10000000);
		        }
				
				
				ps.setInt(6, pid);
				
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

		//list all product by category
		 public static List<Product> getProductByCategory(Product bean) throws IOException { 
			 String Prodcat =  bean.getProduct_category();
			 List<Product> prods = new ArrayList<Product>(); 
			  try { 
				  con = ConnectionManager.getConnection();
				  stmt = con.createStatement(); 
				  ResultSet rs = stmt.executeQuery("select * from product where Product_category='" + Prodcat + "'");
		  
				  while (rs.next()) { 
					  
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
					  
					  Product prod = new Product();
					  prod.setProduct_ID(rs.getInt("Product_ID"));
					  prod.setProduct_name(rs.getString("Product_name"));
					  prod.setProduct_price(rs.getDouble("Product_price"));
					  prod.setProduct_stock(rs.getInt("Product_stock"));
					  prod.setProduct_category(rs.getString("Product_category"));
					  prod.setProdimg(base64Image);
					  
					  prods.add(prod);
		  
				  } 
			  } catch (SQLException e) { 
				  e.printStackTrace(); 
			}
			  return prods;
		 }
		 
		//get product stock
		 public static int getStock(Product bean) {
			 int stock = 0;
			 int pid = bean.getProduct_ID();
			 
			 try {
				 con = ConnectionManager.getConnection();
				  stmt = con.createStatement(); 
				  ResultSet rs = stmt.executeQuery("select Product_stock from product where Product_ID='" + pid + "'");		        
		       
		        while (rs.next()) {
		        	stock = rs.getInt("Product_stock");
		        } 
			  } catch (SQLException e) { 
				  e.printStackTrace(); 
			}
			  return stock;
		 }
		 
		//update product stock
		 public void updateStock(Product bean) {
			 int pstock = bean.getProduct_stock();
			 int pid = bean.getProduct_ID();
			 
			 try {
				 con = ConnectionManager.getConnection();
				 ps=con.prepareStatement("update product set Product_stock=? where Product_ID=?");		        
		        
				 ps.setInt(1, pstock);
				 ps.setInt(2, pid);
					
					//4. execute query
					ps.executeUpdate();
					
					//5. close the connection object
					con.close();
					
					}catch(Exception ex) {
						System.out.println(ex);
					}
					
				}
}
