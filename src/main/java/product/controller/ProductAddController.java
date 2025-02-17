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
import staff.dao.StaffDAO;

/**
 * Servlet implementation class ProductAddController
 */
@WebServlet("/ProductAddController")
@MultipartConfig(fileSizeThreshold=1024*1024*10, maxFileSize=1024*1024*50,maxRequestSize=1024*1024*100)
public class ProductAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ProductDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAddController() {
        super();
        dao = new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//create object, retrive submitted data,set the value
    	HttpSession session = request.getSession();  
		int staffid = (Integer)session.getAttribute("staffid");
		Product prod = new Product();
		prod.setProduct_name(request.getParameter("productname"));
		prod.setProduct_price(Double.parseDouble(request.getParameter("productprice")));
		prod.setProduct_category(request.getParameter("productcategory"));
		prod.setProduct_stock(Integer.parseInt(request.getParameter("productstock")));
		prod.setStaff_ID(staffid);
		
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
        

		//invoke add method in ProductDAO
		dao.add(prod);
		
		request.setAttribute("prod",ProductDAO.getRecentlyadded());
		
		//foward the request
		RequestDispatcher view = request.getRequestDispatcher("/productAdded.jsp");
		view.forward(request,response);
	}

}
