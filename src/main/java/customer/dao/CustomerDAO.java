package customer.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;

	import db.connection.ConnectionManager;
import customer.model.Customer;

	public class CustomerDAO {
		static Connection con=null;
		static Statement stmt = null;
		static ResultSet rs = null;
		static PreparedStatement ps=null;
		
		String Cust_name,Cust_address,Cust_gender,Cust_email,Cust_phone_no,Cust_password;
		Date Cust_DOB;
		
		//customer login
		public static Customer login(Customer bean) {
			// preparing some objects for connection 		
			Statement stmt = null;
			String custemail = bean.getCust_email();
			String password = bean.getCust_password();
			String searchQuery = "select * from customer where Cust_email='" + custemail + "' AND Cust_password='" + password + "'";
			//------prepared statement
			
			try {
				// connect to DB
				con = ConnectionManager.getConnection();
				stmt = con.createStatement();
				
				rs = stmt.executeQuery(searchQuery);
				boolean more = rs.next();

				// if customer does not exist
				if (!more) {
					System.out.println("Sorry, please try again! ");
					bean.setValid(false);
				}
				// if customer exists
				else if (more) {
					String customeremail = rs.getString("Cust_email");
					int customerid = rs.getInt("Cust_ID");
					bean.setCust_email(customeremail);
					bean.setCust_ID(customerid);
					bean.setValid(true);

				}
			} catch (Exception ex) {
				System.out.println("Log In failed: An Exception has occurred! " + ex);
			} // some exception handling
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) { }
					rs = null;
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (Exception e) { }
					stmt = null;
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) { }
					con = null;
				}
			}
			return bean;
		}
		
		//add or register customer
		public void add(Customer bean) {
			//get customer
			Cust_name = bean.getCust_name();
			Cust_address = bean.getCust_address();
			Cust_DOB = bean.getCust_DOB();
			//long dob = Cust_DOB;
			Cust_gender = bean.getCust_gender();
			Cust_email = bean.getCust_email();
			Cust_phone_no = bean.getCust_phone_no();
			Cust_password = bean.getCust_password();
			
			//query to database
			try {
			// 2. create the connection object 1. at connectionmanager
			con = ConnectionManager.getConnection();
			
			//3. create the statement object
			ps=con.prepareStatement("insert into customer (Cust_name,Cust_address,Cust_DOB,Cust_gender,Cust_email,Cust_phone_no,Cust_password)values(?,?,?,?,?,?,?)");
			ps.setString(1, Cust_name);
			ps.setString(2, Cust_address);
			java.sql.Date sqlDate = new java.sql.Date(Cust_DOB.getTime());
			ps.setDate(3, sqlDate);	//check ni nanti
			ps.setString(4, Cust_gender);
			ps.setString(5, Cust_email);
			ps.setString(6, Cust_phone_no);
			ps.setString(7, Cust_password);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close the connection object
			con.close();
			
			}catch(Exception ex) {
				System.out.println(ex);
			}
		}
		 
		//list all customer
			 public static List<Customer> getAllCustomer() { 
				  List<Customer> custs = new ArrayList<Customer>(); 
				  try { 
					  con = ConnectionManager.getConnection();
					  stmt = con.createStatement(); 
					  ResultSet rs = stmt.executeQuery("select * from customer order by Cust_ID");
			  
					  while (rs.next()) { 
						  Customer cust = new Customer();
						  cust.setCust_ID(rs.getInt("Cust_ID"));	  
						  cust.setCust_name(rs.getString("Cust_name"));
						  cust.setCust_address(rs.getString("Cust_address"));
						  cust.setCust_DOB(rs.getDate("Cust_DOB"));
						  cust.setCust_gender(rs.getString("Cust_gender"));
						  cust.setCust_email(rs.getString("Cust_email"));
						  cust.setCust_phone_no(rs.getString("Cust_phone_no"));
						  cust.setCust_password(rs.getString("Cust_password"));
						  custs.add(cust);
			  
					  } 
				  } catch (SQLException e) { 
					  e.printStackTrace(); 
				}
				  return custs;
			 }
			//view customer
			 public static Customer getCustById(int cust_ID) {
					Customer cust = new Customer();
				    try {
				    	 //2. create the connection object 			    	
				    	con = ConnectionManager.getConnection();
				        
				    	//3. create the statement object 
				    	ps=con.prepareStatement("select * from customer where Cust_ID=?");		        
				        ps.setInt(1, cust_ID);
				        
				        //4. execute query 		        
				        ResultSet rs = ps.executeQuery();

				        if (rs.next()) {	            
				        	  cust.setCust_ID(rs.getInt("Cust_ID"));	  
							  cust.setCust_name(rs.getString("Cust_name"));
							  cust.setCust_address(rs.getString("Cust_address"));
							  cust.setCust_DOB(rs.getDate("Cust_DOB"));
							  cust.setCust_gender(rs.getString("Cust_gender"));
							  cust.setCust_email(rs.getString("Cust_email"));
							  cust.setCust_phone_no(rs.getString("Cust_phone_no"));
							  cust.setCust_password(rs.getString("Cust_password"));
				        }
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }

				    return cust;
				}
			 //update customer
			 public void update(Customer bean) {
				 
				 	int Cust_ID = bean.getCust_ID();
				    String Cust_name = bean.getCust_name();
					String Cust_address = bean.getCust_address();
					Date Cust_DOB = bean.getCust_DOB();
					java.sql.Date sqlDate = new java.sql.Date(Cust_DOB.getTime());
					String Cust_gender = bean.getCust_gender();
					String Cust_email = bean.getCust_email();
					String Cust_phone_no = bean.getCust_phone_no();
					String Cust_password = bean.getCust_password();
				 
				 String searchQuery = "UPDATE customer SET Cust_ID='" + Cust_ID + "',Cust_name='" + Cust_name + "',Cust_address='" + Cust_address + "',Cust_DOB='" + sqlDate + "',Cust_gender='" + Cust_gender + "',Cust_email='" + Cust_email + "',Cust_phone_no='" + Cust_phone_no +  "',Cust_password='" + Cust_password + "'WHERE Cust_ID='" + Cust_ID + "' "; 

				//query to database	
				 try {
					// 2. create the connection object 1. at connectionmanager
					con = ConnectionManager.getConnection();
					
					//3. create the statement object
					stmt =con.createStatement();	 
					
					
					
					//4. execute query
					stmt.executeUpdate(searchQuery);
					
					//5. close the connection object
					con.close();
					
					}catch(Exception ex) {
						System.out.println(ex);
					}
			
				}
			 
			 //to check password
			 public static Customer passwordCheck(Customer bean) {
					// preparing some objects for connection 		
					Statement stmt = null;
					int custid = bean.getCust_ID();
					String password = bean.getCust_password();
					String searchQuery = "select * from customer where Cust_ID='" + custid + "' AND Cust_password='" + password + "'";
					//------prepared statement
					
					try {
						// connect to DB
						con = ConnectionManager.getConnection();
						stmt = con.createStatement();
						
						rs = stmt.executeQuery(searchQuery);
						boolean more = rs.next();

						// if password not correct
						if (!more) {
							System.out.println("Wrong password, please try again! ");
							bean.setCorrectpassword(false);
						}
						// if password correct
						else if (more) {
							String customerpass = rs.getString("Cust_password");
							int customerid = rs.getInt("Cust_ID");
							bean.setCust_password(customerpass);
							bean.setCust_ID(customerid);
							bean.setCorrectpassword(true);

						}
					} catch (Exception ex) {
						System.out.println("Log In failed: An Exception has occurred! " + ex);
					} // some exception handling
					finally {
						if (rs != null) {
							try {
								rs.close();
							} catch (Exception e) { }
							rs = null;
						}
						if (stmt != null) {
							try {
								stmt.close();
							} catch (Exception e) { }
							stmt = null;
						}
						if (con != null) {
							try {
								con.close();
							} catch (Exception e) { }
							con = null;
						}
					}
					return bean;
				}
			 
			 //delete customer
			 public void deleteCustomer(int Cust_ID) {
				    try {
				    	//2. create the connection object
				    	con = ConnectionManager.getConnection();
				    	
				    	//3. create the statement object
				    	ps= con.prepareStatement("delete from customer where Cust_ID=?");
				        ps.setInt(1, Cust_ID);
				        
				        //4. execute query
				        ps.executeUpdate();

				    } catch (SQLException e) {
				        e.printStackTrace();
				    }
				}
			 
			//update customer password
			 public void updatePassword(Customer bean) {
				 
				 	int Cust_ID = bean.getCust_ID();
					String Cust_password = bean.getCust_password();
				 
				 String searchQuery = "UPDATE customer SET Cust_ID='" + Cust_ID +  "',Cust_password='" + Cust_password + "'WHERE Cust_ID='" + Cust_ID + "' "; 

				//query to database	
				 try {
					// 2. create the connection object 1. at connectionmanager
					con = ConnectionManager.getConnection();
					
					//3. create the statement object
					stmt =con.createStatement();	 
					
					
					
					//4. execute query
					stmt.executeUpdate(searchQuery);
					
					//5. close the connection object
					con.close();
					
					}catch(Exception ex) {
						System.out.println(ex);
					}
			
				}
			}
