package ch09;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SetSessionValue
 */
@WebServlet("/ch09/setSession")
public class SetSessionValue extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 세션의 유효시간 5초로 (default값은 1800초),  web.xml에서 기본유효시간을 바꿀수도있다.
//		session.setMaxInactiveInterval(5);
		
		String id = session.getId();
		int maxInactiveInterval = session.getMaxInactiveInterval();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>isNew: "+ session.isNew() + "</h1>");
		out.print("<h1>id: "+ id + "</h1>");
		out.print("<h1>maxInactiveInterval: "+ maxInactiveInterval + "</h1>");
			
		//세션을 강제로 삭제
		session.invalidate();
	}

}
