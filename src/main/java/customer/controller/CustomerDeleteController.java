package customer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.dao.CartDAO;
import customer.dao.CustomerDAO;
import order.dao.OrderDAO;

/**
 * Servlet implementation class CustomerDeleteController
 */
@WebServlet("/CustomerDeleteController")
public class CustomerDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CustomerDAO custdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDeleteController() {
        super();
        custdao = new CustomerDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  
		int custid = (Integer)session.getAttribute("custid");
		custdao.deleteCustomer(custid);
		//request.setAttribute("stfs",StaffDAO.getAllStaff());
		request.getRequestDispatcher("SuccessfullyDeleteAccount.jsp").include(request,
				response);
	}

}
