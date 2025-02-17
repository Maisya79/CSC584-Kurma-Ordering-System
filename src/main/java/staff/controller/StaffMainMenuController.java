package staff.controller;

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
import staff.dao.StaffDAO;

/**
 * Servlet implementation class StaffMainMenuController
 */
@WebServlet("/StaffMainMenuController")
public class StaffMainMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ProductDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffMainMenuController() {
        super();
        dao = new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);  
	    int staffid = (Integer)session.getAttribute("staffid");
		request.setAttribute("stf",StaffDAO.getStaffById(staffid));
		
		request.setAttribute("prods",ProductDAO.getAllProduct());
		RequestDispatcher view = request.getRequestDispatcher("/StaffMenu.jsp");
		view.forward(request, response);
	}
}
