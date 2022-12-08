package ch06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForeignCurrency
 */
@WebServlet("/ch06/params")
public class Params extends HttpServlet {
	private static final int DEFAULT_COUNT = 5;
	private static final String[] FOOD_LIST = {"짜장면", "짬뽕", "짬짜면"};
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Query String 처리 localhost:8080/jw/ch06/params?uid=hong&cnt=5
		System.out.println("Params.doPost() method");
		request.setCharacterEncoding("utf-8");			// 굳이 안해도 인코딩 오류 발생하지 않음
		
		String uid = request.getParameter("uid");
		
		// cnt 오류처리( cnt입력을 안했을경우나 문자가 입력되는등의 오류일어날경우)
		int cnt = DEFAULT_COUNT;		//default value
		try {
			String cnt_ = request.getParameter("cnt");
			cnt = Integer.parseInt(cnt_);
		} catch (Exception e) { }
		for (int i=0; i<cnt; i++)
			System.out.println("uid: " + uid);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Params.doPost() method");
		request.setCharacterEncoding("utf-8");			//한글처리를 위해서 반드시 필요(안할시 한글처리가안됨)
		
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String[] skills = request.getParameterValues("skill");
		String food = request.getParameter("food");
		
		String data = "uid: " + uid + "\n";
		data += "pwd: " + pwd + "\n";
		for (String skill :skills)
			data += "skill: " + skill +"\n";
		data += "food: " + FOOD_LIST[Integer.parseInt(food) - 1] +"\n";
		System.out.println(data);
		
		// http 화면단에 띄우기
		response.setCharacterEncoding("utf-8");		// 굳이 안해도 인코딩 오류 발생하지 않음(필요할때만)
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
		out.print("	<h1>Params.doPost() method로 받은 파라메터</h1>");
		out.print("	<hr>");	
		out.print(" <ul>");
		out.print("<li>uid: " + uid + "</li>");
		out.print("<li>pwd: " + pwd + "</li>");
		for(String skill : skills)
			out.print("<li>skill: " + skill + "</li>");
		out.print("<li>food: " + FOOD_LIST[Integer.parseInt(food) - 1] + "</li>");
		out.print("	</ul>");
		
		out.print("</body>");
		out.print("</html>");
	}

}
