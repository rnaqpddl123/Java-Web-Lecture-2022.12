package ch09;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetCookieValue
 */
@WebServlet("/ch09/setCookie")
public class SetCookieValue extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
				
		// cookie는 여러개 설정가능
		Cookie c1 = new Cookie("cookie-name", "cookie-value");
		c1.setMaxAge(24*60*60);		// cookie의 유효기간: 1일 (최종접속시간으로부터 1일)
		response.addCookie(c1);
		
		String kMsg = URLEncoder.encode("한글 데이터", "utf-8");	//한글을 보내기위한 인코딩
		Cookie c2 = new Cookie("hangul-test", kMsg);
		c2.setMaxAge(-1);			// 브라우저가 닫히면 cookie가 사라짐 (Session cookie)
		response.addCookie(c2);
		
		out.print("<h1>현재시간: " + new Date() + "</h1>");
		out.print("<h3> 현재시간을 Cookie로 저장했습니다.</h3>");
	}	
}

