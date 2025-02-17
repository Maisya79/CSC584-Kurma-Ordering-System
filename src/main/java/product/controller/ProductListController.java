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
 * Servlet implementation class ProductListController
 */
@WebServlet("/ProductListController")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ProductDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListController() {
        super();
        dao = new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("prods",ProductDAO.getAllProduct());
		RequestDispatcher view = request.getRequestDispatcher("/productList.jsp");
		view.forward(request, response);
	}

}
