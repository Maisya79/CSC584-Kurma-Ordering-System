package staff.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.dao.ProductDAO;
import staff.dao.StaffDAO;
import staff.model.Staff;

/**
 * Servlet implementation class StaffLoginServlet
 */
@WebServlet("/StaffLoginServlet")
public class StaffLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			Staff stf = new Staff();
			stf.setStaff_email(request.getParameter("staffemail"));
			stf.setStaff_password(request.getParameter("staffpassword"));
			stf = StaffDAO.login(stf);

			if (stf.isValid()) { // logged-in page
				HttpSession session = request.getSession(true);  
				session.setAttribute("staffemail", stf.getStaff_email());
				session.setAttribute("staffid", stf.getStaff_ID());
				request.setAttribute("stf",StaffDAO.getStaffById(stf.getStaff_ID()));

				request.setAttribute("prods",ProductDAO.getAllProduct());
				RequestDispatcher view = request.getRequestDispatcher("/StaffMenu.jsp");
				view.forward(request, response);
			} else response.sendRedirect("StaffInvalidlogin.jsp"); // error page 
		}
		catch(Throwable theException)
		{
			System.out.println(theException);
		}
	}

}
