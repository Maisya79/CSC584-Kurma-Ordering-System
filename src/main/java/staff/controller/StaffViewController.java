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
 * Servlet implementation class StaffViewController
 */
@WebServlet("/StaffViewController")
public class StaffViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       StaffDAO staffdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffViewController() {
        super();
        staffdao = new StaffDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int staff_id = Integer.parseInt(request.getParameter("staff_ID"));
		request.setAttribute("stf",StaffDAO.getStaffById(staff_id));
		RequestDispatcher view = request.getRequestDispatcher("/staffView.jsp");
		view.forward(request, response);
	}
}
