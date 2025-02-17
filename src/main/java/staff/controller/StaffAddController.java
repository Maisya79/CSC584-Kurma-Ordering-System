package staff.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import product.dao.ProductDAO;
import staff.dao.StaffDAO;
import staff.model.Staff;


/**
 * Servlet implementation class StaffAddController
 */
@WebServlet("/StaffAddController")
public class StaffAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       StaffDAO staffdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffAddController() {
        super();
        staffdao = new StaffDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Staff stf = new Staff();
		stf.setStaff_name(request.getParameter("staffname"));
		stf.setStaff_email(request.getParameter("staffemail"));
		stf.setStaff_phone_no(request.getParameter("staffphoneno"));
		stf.setStaff_password(request.getParameter("staffpassword"));

		//invoke add method in ProductDAO
		staffdao.add(stf);
		
		//foward the request
		request.setAttribute("stf",StaffDAO.getRecentlyadded());
		RequestDispatcher view = request.getRequestDispatcher("/staffAdded.jsp");
		view.forward(request,response);
	}

}
