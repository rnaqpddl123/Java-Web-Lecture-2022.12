package ch07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet("/ch07/PlayerList")
public class PlayerServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlayerDao dao = new PlayerDao();
		List<Player> list = dao.getPlayer();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html lang = \"ko\">");
		out.print("<head>");
		out.print("	<meta charset=\"UTF-8\">");
		out.print("	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("	<title>선수 정보</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("	<h1>선수리스트</h1>");
		out.print("	<hr>");
		out.print("	<table border=\"1\">");
		out.print("		<tr>");
		out.print("	 		<th>등번호</th><th>선수이름</th><th>포지션</th><th>생일</th><th>키</th><th>액션</th>");
		out.print("		</tr>");

		for (Player p : list) {
			out.print("	<tr>");
			out.print("		<td>" + p.getBacknum() + "</td>");
			out.print("		<td>" + p.getName() + "</td>");
			out.print("		<td>" + p.getPosition() + "</td>");
			out.print("		<td>" + p.getBirthday().toString() + "</td>");
			out.print("		<td>" + p.getHeight() + "</td>");
			out.print("		<td>" + "<a href=\"/jw/ch07/updatePlayer?backnum=" + p.getBacknum() + "\">수정  </a>" +
			"<a href=\"/jw/ch07/deletePlayer?backnum=" + p.getBacknum() + "\">삭제</a>" + "<td>");
			out.print("	</tr>");
		}	
		
		out.print("</table>");
		out.print("	<br>");	
		out.print("<a href=\"/jw/ch07/registerPlayer.html\">선수등록</a>");	
		out.print("</body>");
		out.print("</html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
