package staff.model;

public class Staff {
	int Staff_ID;
	String Staff_name;
	String Staff_email;
	String Staff_phone_no;
	String Staff_password;
	boolean valid;
	
	public int getStaff_ID() {
		return Staff_ID;
	}
	public void setStaff_ID(int staff_ID) {
		Staff_ID = staff_ID;
	}
	public String getStaff_name() {
		return Staff_name;
	}
	public void setStaff_name(String staff_name) {
		Staff_name = staff_name;
	}
	public String getStaff_email() {
		return Staff_email;
	}
	public void setStaff_email(String staff_email) {
		Staff_email = staff_email;
	}
	public String getStaff_phone_no() {
		return Staff_phone_no;
	}
	public void setStaff_phone_no(String staff_phone_no) {
		Staff_phone_no = staff_phone_no;
	}
	public String getStaff_password() {
		return Staff_password;
	}
	public void setStaff_password(String staff_password) {
		Staff_password = staff_password;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	

}
