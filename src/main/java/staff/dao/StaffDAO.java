package staff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.connection.ConnectionManager;
import staff.model.Staff;

public class StaffDAO {
	static Connection con=null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;
	
	String Staff_name,Staff_email,Staff_phone_no,Staff_password;
	int Staff_ID;
	
	public static Staff login(Staff bean) {
		// preparing some objects for connection 		
		Statement stmt = null;
		String stfemail = bean.getStaff_email();
		String password = bean.getStaff_password();
		String searchQuery = "select * from staff where Staff_email='" + stfemail + "' AND Staff_password='" + password + "'";
		//------prepared statement
		
		try {
			// connect to DB
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if staff does not exist
			if (!more) {
				System.out.println("Sorry, please try again! ");
				bean.setValid(false);
			}
			// if staff exists
			else if (more) {
				String staffemail = rs.getString("Staff_email");
				int staffid = rs.getInt("Staff_ID");
				bean.setStaff_email(staffemail);
				bean.setStaff_ID(staffid);
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
	
	//add staff
	public void add(Staff bean) {
		//get staff
		Staff_name = bean.getStaff_name();
		Staff_email = bean.getStaff_email();
		Staff_phone_no = bean.getStaff_phone_no();
		Staff_password = bean.getStaff_password();
		
		//query to database
		try {
		// 2. create the connection object 1. at connectionmanager
		con = ConnectionManager.getConnection();
		
		//3. create the statement object
		ps=con.prepareStatement("insert into staff (Staff_name,Staff_email,Staff_phone_no,Staff_password)values(?,?,?,?)");
		ps.setString(1, Staff_name);
		ps.setString(2, Staff_email);
		ps.setString(3, Staff_phone_no);
		ps.setString(4, Staff_password);
		
		//4. execute query
		ps.executeUpdate();
		
		//5. close the connection object
		con.close();
		
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	//get recently added staff
	 public static Staff getRecentlyadded() {
			Staff stf = new Staff();
		    try {
		    	 //2. create the connection object 			    	
		    	con = ConnectionManager.getConnection();
		    	//3. create the statement object 
		        stmt = con.createStatement();
		        //4. execute query 
		    	ResultSet rs = stmt.executeQuery("select * from staff where Staff_ID= (SELECT MAX(Staff_ID))");		   //selectmax     
		        
		        while (rs.next()) {	            
					  stf.setStaff_ID(rs.getInt("Staff_ID"));	  
					  stf.setStaff_name(rs.getString("Staff_name"));
					  stf.setStaff_email(rs.getString("Staff_email"));
					  stf.setStaff_phone_no(rs.getString("Staff_phone_no"));
					  stf.setStaff_password(rs.getString("Staff_password"));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return stf;
		}
	 
	//list all staff
		 public static List<Staff> getAllStaff() { 
			  List<Staff> stfs = new ArrayList<Staff>(); 
			  try { 
				  con = ConnectionManager.getConnection();
				  stmt = con.createStatement(); 
				  ResultSet rs = stmt.executeQuery("select * from Staff order by Staff_ID");
		  
				  while (rs.next()) { 
					  Staff stf = new Staff();
					  stf.setStaff_ID(rs.getInt("Staff_ID"));	  
					  stf.setStaff_name(rs.getString("Staff_name"));
					  stf.setStaff_email(rs.getString("Staff_email"));
					  stf.setStaff_phone_no(rs.getString("Staff_phone_no"));
					  stf.setStaff_password(rs.getString("Staff_password"));
					  stfs.add(stf);
		  
				  } 
			  } catch (SQLException e) { 
				  e.printStackTrace(); 
			}
			  return stfs;
		 }
		//view staff
		 public static Staff getStaffById(int staff_ID) {
				Staff stf = new Staff();
			    try {
			    	 //2. create the connection object 			    	
			    	con = ConnectionManager.getConnection();
			        
			    	//3. create the statement object 
			    	ps=con.prepareStatement("select * from Staff where Staff_ID=?");		        
			        ps.setInt(1, staff_ID);
			        
			        //4. execute query 		        
			        ResultSet rs = ps.executeQuery();

			        if (rs.next()) {	            
						  stf.setStaff_ID(rs.getInt("Staff_ID"));	  
						  stf.setStaff_name(rs.getString("Staff_name"));
						  stf.setStaff_email(rs.getString("Staff_email"));
						  stf.setStaff_phone_no(rs.getString("Staff_phone_no"));
						  stf.setStaff_password(rs.getString("Staff_password"));
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }

			    return stf;
			}
		 //update staff
		 public void update(Staff bean) {
			 
			 int Staff_ID = bean.getStaff_ID();
			 String Staff_name = bean.getStaff_name();
			 String Staff_email = bean.getStaff_email();
			 String Staff_phone_no = bean.getStaff_phone_no();
			 String Staff_password = bean.getStaff_password();
			 
			 String searchQuery = "UPDATE Staff SET Staff_ID='" + Staff_ID + "',Staff_name='" + Staff_name + "',Staff_email='" + Staff_email + "',Staff_phone_no='" + Staff_phone_no +  "',Staff_password='" + Staff_password + "'WHERE Staff_ID='" + Staff_ID + "' "; 

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
		 
		 //delete staff
		 public void deleteStaff(int Staff_ID) {
			    try {
			    	//2. create the connection object
			    	con = ConnectionManager.getConnection();
			    	
			    	//3. create the statement object
			    	ps= con.prepareStatement("delete from Staff where Staff_ID=?");
			        ps.setInt(1, Staff_ID);
			        
			        //4. execute query
			        ps.executeUpdate();

			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
		}
