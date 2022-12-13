package ch09.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller
 */
@WebServlet(
		name = "UserController", 
		urlPatterns = { 
				"/ch09/users/list", 
				"/ch09/users/login", 
				"/ch09/users/logout",
				"/ch09/users/register"
		})
public class Controller extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		
		if (requestUri.contains("list")) {
			List<User> list = dao.listUsers();
			RequestDispatcher rd = request.getRequestDispatcher("/ch09/users/listView");
			request.setAttribute("userList", list);
			rd.forward(request, response);
		} else if (requestUri.contains("logout")) {
			System.out.println(session.getAttribute("uid"));
			System.out.println(session.getAttribute("uname"));
			// session 정보제거(로그아웃)
			session.invalidate();
			response.sendRedirect("/jw/ch09/users/list");
		} else {
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (requestUri.contains("login")) {
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			User u = dao.getUserinfo(uid);
			if (u.getUid() != null) {	// uid가 존재
				System.out.println(u.getUid() + ", " + u.getUname());
				// 로그인을 했을때 session에 정보 저장
				session.setAttribute("uid", u.getUid());
				session.setAttribute("uname", u.getUname());
				
				// Welcome message
				out.print("<script>");
				out.print("alert('" + u.getUname() + "님 환영합니다." + "');");
				out.print("location.href = '" + "/jw/ch09/users/list" + "';");
				out.print("</script>");
			} else { 			// uid가 없음
				System.out.println("없는 사용자입니다.");
				
				// 회원 가입 페이지로 안내
				out.print("<script>");
				out.print("alert(회원가입페이지로 이동합니다.)");
				out.print("location.href = '" + "/jw/ch09/users/register.html" + "';");
				out.print("</script>");
			}
			
		} else if (requestUri.contains("register")) {
			
		} else {
			
		}
	}

}
