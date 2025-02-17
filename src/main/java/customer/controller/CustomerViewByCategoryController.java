package customer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.dao.ProductDAO;
import product.model.Product;

/**
 * Servlet implementation class CustomerViewByCategoryController
 */
@WebServlet("/CustomerViewByCategoryController")
public class CustomerViewByCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerViewByCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product_cat = request.getParameter("product_category");
		Product prod = new Product();
		prod.setProduct_category(product_cat);
		request.setAttribute("prods",ProductDAO.getProductByCategory(prod));
		RequestDispatcher view = request.getRequestDispatcher("/customerViewProductByCategory.jsp");
		view.forward(request, response);
	}
}
