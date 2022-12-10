package ch07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerUpdate
 */
@WebServlet("/ch07/updatePlayer")
public class PlayerUpdate extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int backnum = Integer.parseInt(request.getParameter("backnum"));
		
		PlayerDao dao = new PlayerDao();
		Player p = dao.getPlayer(backnum);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String data = "<!DOCTYPE html>"
				+ "<html lang=\"ko\">"
				+ "<head>"
				+ "    <meta charset=\"UTF-8\">"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "    <title>회원 정보 수정</title>"
				+ "    <style>"
				+ "        td {"
				+ "            text-align: center;"
				+ "            padding: 3px;"
				+ "        }"
				+ "    </style>"
				+ "</head>"
				+ "<body style=\"margin: 40px;\">"
				+ "    <h1>회원 정보 수정</h1>"
				+ "    <hr>"
				+ "    <form action=\"/jw/ch07/updatePlayer\" method=\"post\">";
		data += "<input type=\"hidden\" name=\"backnum\" value=\"" +p.getBacknum() +"\">";
		data += "        <table>"
				+ "            <tr>"
				+ "                <td>등번호: </td>";
		data += "<td><input type=\"number\" name=\"backnum\" value=\"" + p.getBacknum()+"\" placeholder=\"숫자만입력하세요\" disabled></td>"; 
		data += "</tr>"
				+ "            <tr>"
				+ "                <td>이름: </td>";
		data += "<td><input type=\"text\" name=\"name\" value=\"" + p.getName() + "\"></td>";
		data += "</tr>"
				+ "            <tr>"
				+ "                <td>포지션: </td>";
		data += "<td>투수<input type=\"radio\" name=\"position\" value=\"투수\" checked>";
		data += "포수<input type=\"radio\" name=\"position\" value=\"포수\">";
		data += "내야수<input type=\"radio\" name=\"position\" value=\"내야수\">";
		data += "외야수<input type=\"radio\" name=\"position\" value=\"외야수\"></td>";
		data += "</tr>"
				+ "            <tr>"
				+ "                <td>생일: </td>";
		data += "<td><input type=\"date\" name=\"birthday\" value=\""+ p.getBirthday() +"\"></td>";
		data += "</tr>"
				+ "            <tr>"
				+ "                <td>키: </td>";
		data += "<td><input type=\"number\" name=\"height\" value=\""+ p.getHeight() +"\"></td>";
		data += " </tr>"
				+ "            <tr>"
				+ "                <td colspan=\"5\"><input type=\"submit\" value=\"수정\"></td>"
				+ "            </tr>"
				+ "            "
				+ "        </table>"
				+ "    </form>"
				+ "    "
				+ "</body>"
				+ "</html>";
		out.print(data);
		
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int backnum = Integer.parseInt(request.getParameter("backnum"));
		System.out.println(backnum);
		String name = request.getParameter("name");
		System.out.println(name);
		String position = request.getParameter("position");
		System.out.println(position);
		String birthday = request.getParameter("birthday");
		System.out.println(birthday);
		int height = Integer.parseInt(request.getParameter("height"));
		System.out.println(height);
		
		Player p = new Player(backnum, name, position, birthday, height);
		PlayerDao dao = new PlayerDao();
		dao.updatePlayer(p);
		
		response.sendRedirect("/jw/ch07/PlayerList");
		
	}

}
