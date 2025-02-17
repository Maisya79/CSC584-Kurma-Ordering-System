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
import javax.servlet.http.HttpSession;

import customer.dao.CustomerDAO;
import customer.model.Customer;

/**
 * Servlet implementation class CustomerUpdateProfile
 */
@WebServlet("/CustomerUpdateProfile")
public class CustomerUpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CustomerDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateProfile() {
        super();
        dao = new CustomerDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  
		int custid = (Integer)session.getAttribute("cust_ID");
		try {
				Customer cust = new Customer();
				cust.setCust_ID(custid);
				cust.setCust_password(request.getParameter("custpassword"));
				cust = CustomerDAO.passwordCheck(cust);
				
				if (cust.isCorrectpassword()) {
			    cust.setCust_ID(custid);
				cust.setCust_password(request.getParameter("custpassword"));
				cust.setCust_name(request.getParameter("custname"));
				cust.setCust_address(request.getParameter("custaddress"));
				String dob =request.getParameter("custdob");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date utilDate= df.parse(dob);
				cust.setCust_DOB(utilDate);
				cust.setCust_gender(request.getParameter("custgender"));
				cust.setCust_email(request.getParameter("custemail"));
				cust.setCust_phone_no(request.getParameter("custphoneno"));
				

				
				dao.update(cust);
				request.setAttribute("cust",CustomerDAO.getCustById(custid));
					RequestDispatcher rd = request.getRequestDispatcher("customerUpdated.jsp");
					rd.forward(request, response);
		        }else {
		        	request.setAttribute("cust",CustomerDAO.getCustById(custid));
		    		
		    		 
		    		session.setAttribute("cust_ID", custid);
		        	RequestDispatcher rd = request.getRequestDispatcher("customerEditwp.jsp");
					rd.forward(request, response);
		        	//response.sendRedirect("customerEditwp.jsp");
		        }
	    } // error page 
				catch(Throwable theException)
		{
			System.out.println(theException);
		}
	}

}
