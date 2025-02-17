package customer.model;

import java.util.Date;

public class Customer {

	int Cust_ID;
	String Cust_name,Cust_address,Cust_gender,Cust_email,Cust_phone_no,Cust_password;
	Date Cust_DOB;
	boolean valid,correctpassword;
	public int getCust_ID() {
		return Cust_ID;
	}
	public void setCust_ID(int cust_ID) {
		Cust_ID = cust_ID;
	}
	public String getCust_name() {
		return Cust_name;
	}
	public void setCust_name(String cust_name) {
		Cust_name = cust_name;
	}
	public String getCust_address() {
		return Cust_address;
	}
	public void setCust_address(String cust_address) {
		Cust_address = cust_address;
	}
	public String getCust_gender() {
		return Cust_gender;
	}
	public void setCust_gender(String cust_gender) {
		Cust_gender = cust_gender;
	}
	public String getCust_email() {
		return Cust_email;
	}
	public void setCust_email(String cust_email) {
		Cust_email = cust_email;
	}
	public String getCust_phone_no() {
		return Cust_phone_no;
	}
	public void setCust_phone_no(String cust_phone_no) {
		Cust_phone_no = cust_phone_no;
	}
	public String getCust_password() {
		return Cust_password;
	}
	public void setCust_password(String cust_password) {
		Cust_password = cust_password;
	}
	public Date getCust_DOB() {
		return Cust_DOB;
	}
	public void setCust_DOB(Date cust_DOB) {
		Cust_DOB = cust_DOB;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean isCorrectpassword() {
		return correctpassword;
	}
	public void setCorrectpassword(boolean correctpassword) {
		this.correctpassword = correctpassword;
	}
	
	
}
