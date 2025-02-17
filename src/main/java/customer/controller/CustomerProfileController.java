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
import product.dao.ProductDAO;

/**
 * Servlet implementation class CustomerProfileController
 */
@WebServlet("/CustomerProfileController")
public class CustomerProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		int custsession = (Integer)session.getAttribute("custid");
		
		//foward the request
				request.setAttribute("cust",CustomerDAO.getCustById(custsession));
				RequestDispatcher view = request.getRequestDispatcher("/customerProfile.jsp");
				view.forward(request, response);
	}

}
