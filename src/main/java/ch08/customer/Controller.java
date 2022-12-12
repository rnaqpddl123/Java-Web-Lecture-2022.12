package ch08.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Controller 통합작업
 */
@WebServlet({"/ch08/customer/list", "/ch08/customer/register", "/ch08/customer/update", "/ch08/customer/delete" })
//@WebServlet("/ch08/customer/*")
public class Controller extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 어떤 동작을 실행시키기 알기위해서
//		String servletPath = request.getServletPath();
		String requestUri = request.getRequestURI();
		
		CustomerDao dao = new CustomerDao();
		response.setContentType("text/html; charset=utf-8");
		
		
		if (requestUri.indexOf("list") > 0) {
			List<Customer> list = dao.getCustomers();
			request.setAttribute("customerList", list);
			RequestDispatcher rd = request.getRequestDispatcher("/ch08/customer/listView");
			rd.forward(request, response);
			
		} else if (requestUri.indexOf("register") > 0) {
			response.sendRedirect("/jw/ch08/customer/register.html");
			
		} else if (requestUri.indexOf("update") > 0) {
			String uid = request.getParameter("uid");
			Customer c = dao.getCustomer(uid);		
			request.setAttribute("customer", c);
			RequestDispatcher rd = request.getRequestDispatcher("/ch08/customer/updateView");
			rd.forward(request, response);
			
		} else if (requestUri.indexOf("delete") > 0) {
			String uid = request.getParameter("uid");
			dao.deleteCustomer(uid);
			response.sendRedirect("/jw/ch08/customer/list");
			
		} else {
			System.out.println(" get의 잘못된경로");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		String servletPath = request.getServletPath();
		String requestUri = request.getRequestURI();
		
		CustomerDao dao = new CustomerDao();
		response.setContentType("text/html; charset=utf-8");
		
		if (requestUri.indexOf("register") > 0) {
			String uid = request.getParameter("uid");
			String uname = request.getParameter("uname");
			
			// 유효성 검증
			Customer c = dao.getCustomer(uid);
			if (c.getUid() != null)			// 기존 id가 중복인경우
				response.sendRedirect("/jw/ch08/customer/register.html");
			else {											// id가 중복이 아닌경우
				c = new Customer(uid, uname);
				dao.insertCustomer(c);
				
				//코드 결과처리후(회원가입) 페이지이동
				response.sendRedirect("/jw/ch08/customer/list");
			}

			
		} else if (requestUri.indexOf("update") > 0) {
			String uid = request.getParameter("uid");
			String uname = request.getParameter("uname");
			Customer c = new Customer(uid, uname);
			dao.updateCustomer(c);
			
			response.sendRedirect("/jw/ch08/customer/list");
			
		} else {
			System.out.println(" post의 잘못된경로");
		}
		
	}

}
