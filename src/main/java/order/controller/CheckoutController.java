package order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.dao.CartDAO;
import order.dao.OrderDAO;
import order.model.Order;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet("/CheckoutController")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       OrderDAO dao;
       CartDAO cdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutController() {
        super();
        dao = new OrderDAO();
        cdao = new CartDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		int custsession = (Integer)session.getAttribute("custid");
		
		double prodtotalprice = Double.parseDouble(request.getParameter("ordertp")); 
		
		//dao.getCartIDs();
		
		Order ord = new Order();
		ord.setCust_ID(custsession);
		ord.setTotal_price(prodtotalprice);

		//invoke add method in ProductDAO
		dao.addOrder(ord);
		cdao.addOrderID();
		
		//foward the request
		request.setAttribute("carts",CartDAO.getAllCart());
		request.setAttribute("ordertotalprice",CartDAO.calculateOrderTotal());
		request.setAttribute("ords",OrderDAO.getCurrentOrder());
		RequestDispatcher view = request.getRequestDispatcher("/orderComplete.jsp");
		view.forward(request, response);
	}

}
