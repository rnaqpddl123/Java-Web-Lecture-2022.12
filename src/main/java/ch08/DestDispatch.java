package ch08;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DestDispatch
 */
@WebServlet("/ch08/dst4")
public class DestDispatch extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		// dispatch방식에서 한글 디코딩용
		String msg = request.getParameter("msg");
		String addr = (String)request.getAttribute("addr");		//타입을 맞춰줘야함 (원래 타입은 Object)

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>Dispatch를 이용한 화면이동</h1>");
		out.print("<h1>" + msg +"</h1>");
		out.print("<h1>" + addr +"</h1>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
