package staff.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import staff.dao.StaffDAO;

/**
 * Servlet implementation class StaffEditController
 */
@WebServlet("/StaffEditController")
public class StaffEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       StaffDAO stf;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffEditController() {
        super();
        stf = new StaffDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int staffid = Integer.parseInt(request.getParameter("staff_ID"));
		request.setAttribute("stf",StaffDAO.getStaffById(staffid));
		
		HttpSession session = request.getSession();  
		session.setAttribute("staff_ID", staffid);
		
			RequestDispatcher view = request.getRequestDispatcher("/staffEdit.jsp");
			
			view.forward(request, response);
	}

}
