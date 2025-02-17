package cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.dao.CartDAO;
import cart.model.Cart;
import order.dao.OrderDAO;
import order.model.Order;
import product.dao.ProductDAO;
import product.model.Product;

/**
 * Servlet implementation class AddOrderController
 */
@WebServlet("/AddOrderController")
public class AddOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       OrderDAO dao;
       ProductDAO pdao;
       CartDAO cdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderController() {
        super();
        dao = new OrderDAO();
        pdao = new ProductDAO();
        cdao = new CartDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		int custsession = (Integer)session.getAttribute("custid");
		//int prodid = (Integer)session.getAttribute("product_ID");
		int pid = Integer.parseInt(request.getParameter("productid"));
		double prodprice = Double.parseDouble(request.getParameter("productprice"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String pname = request.getParameter("productname");
		
		//double totalprice = quantity * prodprice;
		
		Product prod = new Product();
		prod.setProduct_ID(pid);
		int prodstock = ProductDAO.getStock(prod);
		System.out.println(prodstock);
		int updatedstock = prod.CalculateStock(prodstock,quantity);
		System.out.println(updatedstock);
		prod.setProduct_stock(updatedstock);
		//prod.setProduct_ID(pid);
		pdao.updateStock(prod);
		
		Cart cart = new Cart();
		cart.setCust_ID(custsession);
		cart.setProduct_name(pname);
		cart.setProduct_ID(pid);
		cart.setProduct_Quantity(quantity);
		double totalprice = cart.Calculateprice(quantity,prodprice);
		cart.setProduct_total_price(totalprice);

		//invoke add method in ProductDAO
		cdao.addtoCart(cart);
		
		//foward the request
		request.setAttribute("prods",ProductDAO.getAllProduct());
		RequestDispatcher view = request.getRequestDispatcher("/orderKurma.jsp");
		view.forward(request, response);
	}

}
