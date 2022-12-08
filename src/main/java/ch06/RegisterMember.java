package ch06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterMember
 */
@WebServlet("/ch06/regMember")
public class RegisterMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String[] SEX = {"남성", "여성"};
	private static final String[] HOBBY_LIST = {"게임", "낚시", "축구", "농구", "독서"};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegusterMember.doPost() method");
		request.setCharacterEncoding("utf-8");
		// 데이터 가져오기
		String id = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String uname = request.getParameter("uname");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String sex = request.getParameter("sex");
		String[] hobbies = request.getParameterValues("hobby");
		
		
		// http화면
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
		out.print("	<h1>회원가입되었습니다.</h1>");
		out.print("	<hr>");	
		out.print(" <ul>");
		out.print("<li>id: " + id + "</li>");
		out.print("<li>pwd: " + pwd.equals(pwd2) + "</li>");
		out.print("<li>이름: " + uname + "</li>");
		out.print("<li>birthday: " + birthday + "</li>");
		out.print("<li>email: " + email + "</li>");
		out.print("<li>성별: " + SEX[Integer.parseInt(sex)] + "</li>");
		out.print("<li>취미 목록 : ");
		for(String hobby : hobbies)
			out.print(HOBBY_LIST[Integer.parseInt(hobby)] + " ");
		out.print("</li>");
		out.print("	</ul>");
		out.print("	<br>");	
		out.print(" <button onclick=\"location.href='/jw/ch06/registerMember.html'\">재실행</button>");
		
		out.print("</body>");
		out.print("</html>");
		
		
		
	}

}
