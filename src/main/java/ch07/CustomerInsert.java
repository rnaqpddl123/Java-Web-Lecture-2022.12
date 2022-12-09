package ch07;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerInsert
 */
@WebServlet("/ch07/registerCustomer")
public class CustomerInsert extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uname");
		
		CustomerDao dao = new CustomerDao();
		// 유효성 검증
		Customer c = dao.getCustomer(uid);
		if (c.getUid() != null)			// 기존 id가 중복인경우
			response.sendRedirect("/jw/ch07/registerCustomer.html");
		else {											// id가 중복이 아닌경우
			dao = new CustomerDao();
			c = new Customer(uid, uname);
			dao.insertCustomer(c);
			
			//코드 결과처리후(회원가입) 페이지이동
			response.sendRedirect("/jw/ch07/customerList");
		}
			
		
		
		
	}

}
