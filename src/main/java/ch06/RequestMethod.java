package ch06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestMethod
 */
@WebServlet("/ch06/reqMethod")
public class RequestMethod extends HttpServlet {
//	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();		//	/jw
		String method = request.getMethod();				//	GET
		String requestUri = request.getRequestURI();		//  /jw/ch06/reqMethod
		String serverName =  request.getServerName();		//  localhost
		String sevletPath = request.getServletPath();		//  /ch06/reqMethod
		String pathInfo = request.getPathInfo();			//	null
		
		String data = "contextPath: " + contextPath + "\n";
		data += "method: " + method + "\n";
		data += "requestUri: " + requestUri + "\n";
		data += "serverName: " + serverName + "\n";
		data += "sevletPath: " + sevletPath + "\n";
		data += "pathInfo: " + pathInfo + "\n";
		System.out.println(data);
		
		// 화면띄워서 확인
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html lang = \"ko\">");
		out.print("<head>");
		out.print("	<meta charset=\"UTF-8\">");
		out.print("	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("	<title>Insert title here</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("	<h1>HttpServletRequest의 다양한 메소드</h1>");
		out.print("	<hr>");	
		String[] ulList = data.split("\n");
		out.print(" <ul>");
		for (String li:ulList) {
			out.print("		<li>" + li + "</li>");
		}
		out.print("	</ul>");
		
		out.print("</body>");
		out.print("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
