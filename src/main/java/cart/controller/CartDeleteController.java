package cart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.dao.CartDAO;
import product.dao.ProductDAO;
import product.model.Product;

/**
 * Servlet implementation class CartDeleteController
 */
@WebServlet("/CartDeleteController")
public class CartDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CartDAO dao;
       ProductDAO pdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDeleteController() {
        super();
        dao = new CartDAO();
        pdao = new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cartid = Integer.parseInt(request.getParameter("cart_ID"));
		int pid = Integer.parseInt(request.getParameter("product_ID"));
		int quantity = Integer.parseInt(request.getParameter("product_Quantity"));
		
		//add 1 method to add back stock quantity
		Product prod = new Product();
		prod.setProduct_ID(pid);
		int prodstock = ProductDAO.getStock(prod);
		System.out.println(prodstock);
		int updatedstock = prod.addStock(prodstock,quantity);
		System.out.println(updatedstock);
		prod.setProduct_stock(updatedstock);
		//prod.setProduct_ID(pid);
		pdao.updateStock(prod);
		
		dao.deleteCart(cartid);
		request.setAttribute("carts",CartDAO.getAllCart());
		request.setAttribute("totalcartprice",CartDAO.calculateOrderTotal());
		RequestDispatcher view = request.getRequestDispatcher("/orderCart.jsp");
		view.forward(request, response);
	}
}
