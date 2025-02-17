package staff.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staff.dao.StaffDAO;

/**
 * Servlet implementation class StaffDeleteController
 */
@WebServlet("/StaffDeleteController")
public class StaffDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       StaffDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffDeleteController() {
        super();
        dao = new StaffDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int staffid = Integer.parseInt(request.getParameter("staff_ID"));
		dao.deleteStaff(staffid);
		request.setAttribute("stfs",StaffDAO.getAllStaff());
		RequestDispatcher view = request.getRequestDispatcher("/staffList.jsp");
		view.forward(request, response);
	}

}
