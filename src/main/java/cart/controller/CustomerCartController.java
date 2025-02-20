package cart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.dao.CartDAO;

/**
 * Servlet implementation class CustomerCartController
 */
@WebServlet("/CustomerCartController")
public class CustomerCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("carts",CartDAO.getAllCart());
		request.setAttribute("totalcartprice",CartDAO.calculateOrderTotal());
		RequestDispatcher view = request.getRequestDispatcher("/orderCart.jsp");
		view.forward(request, response);
	}
}
