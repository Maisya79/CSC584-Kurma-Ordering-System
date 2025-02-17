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
import staff.model.Staff;



/**
 * Servlet implementation class StaffUpdateController
 */
@WebServlet("/StaffUpdateController")
public class StaffUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       StaffDAO staffdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffUpdateController() {
        super();
        staffdao = new StaffDAO();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();  
		int staffid = (Integer)session.getAttribute("staff_ID");
		String staffname = request.getParameter("staffname");
		String staffemail = request.getParameter("staffemail");
		String staffphoneno = request.getParameter("staffphoneno");
		String staffpassword = request.getParameter("staffpassword");
		
		//create object, retrive submitted data,set the value
				Staff stf = new Staff();
				
				stf.setStaff_ID(staffid);
				stf.setStaff_name(staffname);
				stf.setStaff_email(staffemail);
				stf.setStaff_phone_no(staffphoneno);
				stf.setStaff_password(staffpassword);
				staffdao.update(stf);
				
				request.setAttribute("stf",StaffDAO.getStaffById(staffid));
				
				//foward the request
				RequestDispatcher view = request.getRequestDispatcher("/staffUpdated.jsp");
				view.forward(request,response);
	}

}
