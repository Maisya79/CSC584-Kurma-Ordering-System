package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.dao.ProductDAO;

/**
 * Servlet implementation class ProductViewController
 */
@WebServlet("/ProductViewController")
public class ProductViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ProductDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductViewController() {
        super();
        dao = new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int product_id = Integer.parseInt(request.getParameter("product_ID"));
		request.setAttribute("prod",ProductDAO.getProductById(product_id));
		RequestDispatcher view = request.getRequestDispatcher("/productView.jsp");
		view.forward(request, response);
	}
}
