package ch07;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerInsert
 */
@WebServlet("/ch07/registerPlayer")
public class PlayerInsert extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int backnum = Integer.parseInt(request.getParameter("backnum"));
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String birthday = request.getParameter("birthday");
		int height = Integer.parseInt(request.getParameter("height"));
		
		PlayerDao dao = new PlayerDao();
		//유효성검증
		Player p = dao.getPlayer(backnum);
		if (p.getBacknum() != 0)
			response.sendRedirect("/jw/ch07/registerPlayer.html");
		else {
			dao = new PlayerDao();
			p = new Player(backnum, name, position, birthday, height);
			dao.insertPlayer(p);
			
			response.sendRedirect("/jw/ch07/PlayerList");
		}
		
	}

}
