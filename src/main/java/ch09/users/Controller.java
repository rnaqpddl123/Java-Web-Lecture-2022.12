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

import org.mindrot.jbcrypt.BCrypt;


/**
 * Servlet implementation class Controller
 */
@WebServlet(
		name = "UserController", 
		urlPatterns = { 
				"/ch09/users/list", 
				"/ch09/users/login", 
				"/ch09/users/logout",
				"/ch09/users/register",
				"/ch09/users/update",
				"/ch09/users/delete"
		})
public class Controller extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// TODO: if문 case문으로 교체
		if (requestUri.contains("list")) {
			List<User> list = dao.listUsers();
			rd = request.getRequestDispatcher("/ch09/users/listView");
			request.setAttribute("userList", list);
			rd.forward(request, response);
		} 
		else if (requestUri.contains("logout")) {
			// session 정보제거(로그아웃)
			session.invalidate();
			response.sendRedirect("/jw/ch09/users/list");
		}  
		
		else if (requestUri.contains("register")) {
			response.sendRedirect("/jw/ch09/users/register.html");
		}  
		
		else if (requestUri.contains("delete")) {
			String uid = request.getParameter("uid");
			dao.deleteUser(uid);
			out.print("<script>");
			out.print("alert('id : " + uid + "의 데이터가 삭제되었습니다.');");
			out.print("location.href = '" + "/jw/ch09/users/list" + "';");
			out.print("</script>");
		} 
		
		else if (requestUri.contains("update")) {
			response.sendRedirect("/jw/ch09/users/updateView");
		}  
		
		else {
			System.out.println("get의 잘못된경로");
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
				
				if (BCrypt.checkpw(pwd, u.getPwd())) {		// 비밀번호 같은지 비교(암호화해서)
					// 로그인을 했을때 session에 정보 저장
					session.setAttribute("uid", u.getUid());
					session.setAttribute("uname", u.getUname());
					
					// Welcome message
					out.print("<script>");
					out.print("alert('" + u.getUname() + "님 환영합니다." + "');");
					out.print("location.href = '" + "/jw/ch09/users/list" + "';");
					out.print("</script>");
				}
				else {		
					// 비밀번호가 틀림, 로그인페이지로 다시이동
					out.print("<script>");
					out.print("alert('잘못된 패스워드 입니다. 다시입력하세요. ');");
					out.print("location.href = '" + "/jw/ch09/users/login.html" + "';");
					out.print("</script>");
				}
			} else { 			// uid가 없음
				// 회원 가입 페이지로 안내
				out.print("<script>");
				out.print("alert(회원가입페이지로 이동합니다.)");
				out.print("location.href = '" + "/jw/ch09/users/register.html" + "';");
				out.print("</script>");
			}
			
		} else if (requestUri.contains("register")) {
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			String uname = request.getParameter("uname");
			String email = request.getParameter("email");
			
			User u = dao.getUserinfo(uid);
			if (u.getUid() != null)			// 기존 id가 중복인경우
				response.sendRedirect("/jw/ch09/users/register");
			else {											// id가 중복이 아닌경우
				u = new User(uid, pwd, uname, email);
				dao.registerUser(u);
				
				//코드 결과처리후(회원가입) 페이지이동
				response.sendRedirect("/jw/ch09/users/list");
			}			
			
		} else if (requestUri.contains("update")) {
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			String uname = request.getParameter("uname");
			String email = request.getParameter("email");
			
			User u = dao.getUserinfo(uid);
			
			if (BCrypt.checkpw(pwd, u.getPwd())) {	// 비밀번호 일치하는지 확인
				dao.updateUser(new User(uid,pwd,uname,email));
				
				out.print("<script>");
				out.print("alert('" + u.getUid() + "님 정보가 수정되었습니다." + "');");
				out.print("location.href = '" + "/jw/ch09/users/list" + "';");
				out.print("</script>");
			}
			else { 									// 비밀번호 틀렸을때
				out.print("<script>");
				out.print("alert('패스워드가 일치하지 않습니다. 패스워드를 확인해주세요 ');");
				out.print("location.href = '" + "/jw/ch09/users/update" + "';");
				out.print("</script>");
			}
			
		
		} else {
			System.out.println("post의 잘못된경로");
		}
	}

}
