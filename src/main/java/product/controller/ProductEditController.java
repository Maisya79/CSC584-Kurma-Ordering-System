package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.dao.ProductDAO;
/**
 * Servlet implementation class ProductEditController
 */
@WebServlet("/ProductEditController")
public class ProductEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ProductDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductEditController() {
        super();
        dao = new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productid = Integer.parseInt(request.getParameter("product_ID"));
		request.setAttribute("prod",ProductDAO.getProductById(productid));
		
		HttpSession session = request.getSession();  
		session.setAttribute("product_ID", productid);
		
			RequestDispatcher view = request.getRequestDispatcher("/productEdit.jsp");
			
			view.forward(request, response);
	}
}
