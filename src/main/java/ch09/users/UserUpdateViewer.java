package ch09.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;



@WebServlet("/ch09/users/updateView")
public class UserUpdateViewer extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		// session으로 정보가져오기(비밀번호가 틀렸을때 해당되는 정보를 다시띄우기 위해서)
		String uid = (String)session.getAttribute("uid");
		UserDao dao = new UserDao();
		User u = dao.getUserinfo(uid);
	
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
				+ "    <form action=\"/jw/ch09/users/update\" method=\"post\">";
		data += "<input type=\"hidden\" name=\"uid\" value=\"" + u.getUid() + "\">";
		data += "        <table>"
				+ "            <tr>"
				+ "                <td>사용자 ID</td>";
		data += "<td><input type=\"text\" name=\"uid\" value=\"" + u.getUid() +"\" disabled></td>"; 
		data += "</tr>"
				+ "            <tr>"
				+ "                <td>비밀번호 확인</td>";
		data += "<td><input type=\"password\" name=\"pwd\" placeholder=\"비밀번호를 입력해주세요\"></td>";
		data += "</tr>"
				+ "            <tr>"
				+ "                <td>이름</td>";
		data += "<td><input type=\"text\" name=\"uname\" value=\"" + u.getUname() +"\"></td>";
		data += "</tr>"
				+ "            <tr>"
				+ "                <td>email</td>";
		data += "<td><input type=\"email\" name=\"email\" value=\"" + u.getEmail() +"\"></td>";
		data += "</tr>"
				+ "            <tr>"
				+ "                <td>가입일자</td>";
		data += "<td><input type=\"text\" name=\"regDate\" value=\"" + u.getRegDate() +"\" disabled></td>";
		data += " </tr>"
				+ "            <tr>"
				+ "                <td colspan=\"2\"><input type=\"submit\" value=\"수정\"></td>"
				+ "            </tr>"
				+ "            "
				+ "        </table>"
				+ "    </form>"
				+ "    "
				+ "</body>"
				+ "</html>";
		out.print(data);
	}

}
