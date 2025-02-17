package order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.dao.OrderDAO;

/**
 * Servlet implementation class ListCustomerOrderController
 */
@WebServlet("/ListCustomerOrderController")
public class ListCustomerOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCustomerOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ords",OrderDAO.getAllOrder());
		RequestDispatcher view = request.getRequestDispatcher("/staffListCustomerOrder.jsp");
		view.forward(request, response);
	}

}
