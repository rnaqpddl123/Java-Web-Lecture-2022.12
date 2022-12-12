package ch08;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerUpdateController
 */
@WebServlet("/ch08/updateCustomer")
public class CustomerUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자의 입력을 받음
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		
		// DB에서 필요한 정보를 가져옴
		CustomerDao dao = new CustomerDao();
		Customer c = dao.getCustomer(uid);
		
		// Viewer를 호출
		request.setAttribute("customer", c);
		RequestDispatcher rd = request.getRequestDispatcher("/ch08/updateViewer");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uname");
		Customer c = new Customer(uid, uname);
		CustomerDao dao = new CustomerDao();
		dao.updateCustomer(c);
		
		response.sendRedirect("/jw/ch08/customer");
	}

}
