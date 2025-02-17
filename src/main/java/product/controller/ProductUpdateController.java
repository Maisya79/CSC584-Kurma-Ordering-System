package product.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import product.dao.ProductDAO;
import product.model.Product;
/**
 * Servlet implementation class ProductUpdateController
 */
@WebServlet("/ProductUpdateController")
@MultipartConfig(fileSizeThreshold=1024*1024*10, maxFileSize=1024*1024*50,maxRequestSize=1024*1024*100)
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ProductDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateController() {
        super();
        dao = new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  
		int prodid = (Integer)session.getAttribute("product_ID");
		int staffid = (Integer)session.getAttribute("staffid");
		String productname = request.getParameter("productname");
		double productprice = Double.parseDouble(request.getParameter("productprice"));
		String productcategory = request.getParameter("productcategory");
		int productstock = Integer.parseInt(request.getParameter("productstock"));
		
		//create object, retrive submitted data,set the value
				Product prod = new Product();
				prod.setProduct_ID(prodid);
				prod.setStaff_ID(staffid);
				prod.setProduct_name(productname);
				prod.setProduct_price(productprice);
				prod.setProduct_category(productcategory);
				prod.setProduct_stock(productstock);
				
				//get image file. Credit: Nam Ha Minh at codejava.net
				InputStream inputStream = null; // input stream of the upload file
				 
		        Part filePart = request.getPart("prodimg");
		        
		        
		        if (filePart != null) {
		        	
		           //  prints out some information for debugging
		            System.out.println(filePart.getName());
		            System.out.println(filePart.getSize());
		            System.out.println(filePart.getContentType());
		         
		             
		            // obtains input stream of the upload file
		            inputStream = filePart.getInputStream();
		        }
		        
		        prod.setProdimgadd(inputStream);
				
				dao.update(prod);
				
				request.setAttribute("prod",ProductDAO.getProductById(prodid));
				
				//foward the request
				RequestDispatcher view = request.getRequestDispatcher("/productUpdated.jsp");
				view.forward(request,response);
	}

}
