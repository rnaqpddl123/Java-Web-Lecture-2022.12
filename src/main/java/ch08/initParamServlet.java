package ch08;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class initParamServlet
 */
@WebServlet(
		urlPatterns = { 
				"/ch08/init", 
				"/ch08/init2"
		}, 
		initParams = { 
				@WebInitParam(name = "email", value = "admin@web.com", description = "관리자 이메일 주소"), 
				@WebInitParam(name = "tel", value = "010-1234-5678", description = "관리자 전화번호")
		})
public class initParamServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		String servletPath = request.getServletPath();
		System.out.println("email: " + email);
		System.out.println("tel: " + tel);
		System.out.println("servletPath: " + servletPath);	// ch08/init을 호출할때랑 ch08/init2를 호출할때 다른결과가나옴
		
		if (servletPath.indexOf("init2") >=0 ) 	//ch08/init2를 호출했을경우
			System.out.println("init2에서 처리해주어야하는일");
		else 
			System.out.println("init에서 처리해주어야하는일");
			

		
		
		
	}

}
