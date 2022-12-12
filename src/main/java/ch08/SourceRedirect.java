package ch08;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SourceRefresh
 */
@WebServlet("/ch08/src1")
public class SourceRedirect extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/ch08/src1 doGet()");
		
		String msg = "SourceRedirect, 한글 확인용 메세지";
		msg = URLEncoder.encode(msg, "utf-8");		// 주소창으로 한글을 보낼때는 반드시 encoding을 해주어야함
		System.out.println(msg);	
		// Encoding을 안해주면 SourceRedirect, 한글 확인용 메세지     와 같이 뜨고
		// Encoding을 해주면	SourceRedirect%2C+%ED%95%9C%EA%B8%80+%ED%99%95%EC%9D%B8%EC%9A%A9+%EB%A9%94%EC%84%B8%EC%A7%80   와 같이뜸
				
		request.setAttribute("addr", "서울시 광진구 구의동");
		
		HttpSession session = request.getSession();
		session.setAttribute("addr", "서울시 광진구 구의동");
		
		ServletContext ctx = getServletContext();
		ctx.setAttribute("addr", "서울시 광진구 구의동");
		response.sendRedirect("/jw/ch08/dst1?msg=" + msg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
