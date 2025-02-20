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

/**
 * Servlet implementation class CustomerEditPasswordController
 */
@WebServlet("/CustomerEditPasswordController")
public class CustomerEditPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerEditPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int custid = Integer.parseInt(request.getParameter("cust_ID"));
		request.setAttribute("cust",CustomerDAO.getCustById(custid));
		
		HttpSession session = request.getSession();  
		session.setAttribute("cust_ID", custid);
		
			RequestDispatcher view = request.getRequestDispatcher("/customerEditPassword.jsp");
			
			view.forward(request, response);
	}

}
