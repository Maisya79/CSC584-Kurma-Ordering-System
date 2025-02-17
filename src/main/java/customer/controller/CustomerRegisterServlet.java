package customer.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.dao.CustomerDAO;
import customer.model.Customer;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CustomerDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegisterServlet() {
        super();
        dao = new CustomerDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String dob =request.getParameter("custdob");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate= df.parse(dob);
		
			Customer cust = new Customer();
			cust.setCust_name(request.getParameter("custname"));
			cust.setCust_address(request.getParameter("custaddress"));
			cust.setCust_DOB(utilDate);
			cust.setCust_gender(request.getParameter("custgender"));
			cust.setCust_email(request.getParameter("custemail"));
			cust.setCust_phone_no(request.getParameter("custphoneno"));
			cust.setCust_password(request.getParameter("custpassword"));

			dao.add(cust);
				RequestDispatcher rd = request.getRequestDispatcher("customerLogin.jsp");
				rd.forward(request, response);
	}catch(Throwable theException)
	{
		System.out.println(theException);
	}

}
}
