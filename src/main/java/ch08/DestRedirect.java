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
 * Servlet implementation class DestRedirect
 */
@WebServlet("/ch08/dst1")
public class DestRedirect extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg = request.getParameter("msg");
		// 한글로 받은 파일 Decoding, 지금은 안해도되지만 안될때는 해주어야함
		URLDecoder.decode(msg, "utf-8");
		
		// Client를 갔다가 온 request 이므로 src1에서 보낸 request와 다르다.
		// Request 저장소를 통해서 redirect는 데이터를 전송할 수 없다.
		String addr = (String)request.getAttribute("addr");
		
		HttpSession session = request.getSession();
		String sessAddr = (String)session.getAttribute("addr");
		
		// Application 저장소를 통해 데이터를 전달, 바람직하지않음(자원을 계속 먹고있기 때문)
		ServletContext ctx = getServletContext();
		String ctxAddr = (String)ctx.getAttribute("addr");

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>sendRedirect를 이용한 화면 이동</h1>");
		out.print("<h1>" + msg + "</h1>");
		out.print("<h1>Request: " + addr + "</h1>");
		out.print("<h1>Session: " + sessAddr + "</h1>");
		out.print("<h1>Application: " + ctxAddr + "</h1>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
