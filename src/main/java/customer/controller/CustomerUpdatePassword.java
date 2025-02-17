package customer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.dao.CustomerDAO;
import customer.model.Customer;
import product.dao.ProductDAO;

/**
 * Servlet implementation class CustomerUpdatePassword
 */
@WebServlet("/CustomerUpdatePassword")
public class CustomerUpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CustomerDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdatePassword() {
        super();
        dao = new CustomerDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			HttpSession session = request.getSession();  
			int custid = (Integer)session.getAttribute("cust_ID");
			Customer cust = new Customer();
			cust.setCust_ID(custid);
			cust.setCust_password(request.getParameter("custoldpassword"));
			cust = CustomerDAO.passwordCheck(cust);

			if (cust.isCorrectpassword()) {
				cust.setCust_ID(custid);
				cust.setCust_password(request.getParameter("custnewpassword"));
				dao.updatePassword(cust);
				request.setAttribute("cust",CustomerDAO.getCustById(cust.getCust_ID()));
				request.setAttribute("passupd","Password Updated!");
				RequestDispatcher view = request.getRequestDispatcher("/customerPasswordUpdated.jsp");
				view.forward(request, response);
			} else response.sendRedirect("customerEditPassword.jsp"); // error page 
		}
		catch(Throwable theException)
		{
			System.out.println(theException);
		}
	}
	}
