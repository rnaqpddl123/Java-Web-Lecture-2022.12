package ch06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ch06/gugudan")
public class Gugudan extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String data = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"ko\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>구구단</title>\r\n"
				+ "</head>\r\n"
				+ "<body style=\"margin: 40px;\">\r\n"
				+ "    <h1>구구단</h1>\r\n"
				+ "    <hr>\r\n"
				+ "    <form action=\"/jw/ch06/gugudan\" method=\"post\">\r\n"
				+ "        출력할 구구단: <input type=\"number\" name=\"dan\" id=\"\"><br>\r\n"
				+ "        <input type=\"submit\" value=\"확인\">\r\n"
				+ "    </form>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		out.print(data);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dan_ = request.getParameter("dan");
		int dan = Integer.parseInt(dan_);
		
		String data = "";
		for (int i=1; i<=9; i++)
			data += dan + " x " + i + " = " + dan*i + "\n";
		System.out.println(data);
		
		
		// http 화면단에 띄우기
		request.setCharacterEncoding("utf-8");			
		response.setCharacterEncoding("utf-8");		
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
		out.print("	<h1>구구단 </h1>");
		out.print("	<hr>");	
		String[] pList = data.split("\n");
		out.print("<h3>" + dan + "단</h3>");
		for(String p : pList)
			out.print("<p>" + p + "</p>");
		out.print("	<br>");	
//		out.print(" <button onclick=\"location.href='/jw/ch06/gugudan.html'\">재실행</button>");
		// 자기자신을 불러옴get방식으로
		out.print(" <button onclick=\"location.href='/jw/ch06/gugudan'\">재실행</button>");
		
		out.print("</body>");
		out.print("</html>");
	
	}

}
