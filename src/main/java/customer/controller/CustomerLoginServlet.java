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
 * Servlet implementation class CustomerLoginServlet
 */
@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			Customer cust = new Customer();
			cust.setCust_email(request.getParameter("custemail"));
			cust.setCust_password(request.getParameter("custpassword"));
			cust = CustomerDAO.login(cust);

			if (cust.isValid()) { // logged-in page
				HttpSession session = request.getSession(true);  
				session.setAttribute("custemail", cust.getCust_email());
				session.setAttribute("custid", cust.getCust_ID());
				request.setAttribute("cust",CustomerDAO.getCustById(cust.getCust_ID()));

				request.setAttribute("prods",ProductDAO.getAllProduct());
				RequestDispatcher view = request.getRequestDispatcher("/customerMenu.jsp");
				view.forward(request, response);
			} else response.sendRedirect("customerInvalidLogin.jsp"); // error page 
		}
		catch(Throwable theException)
		{
			System.out.println(theException);
		}
	}
	}
