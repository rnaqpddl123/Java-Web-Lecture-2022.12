package ch14.users3;

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
 * Servlet implementation class UserServiceController
 * session에서 uname저장안하고 uid만 저장하게 바꾸고 update에서 request로 필요한정보 보내게 바꿈
 */
@WebServlet({ "/ch14/users3/list", "/ch14/users3/login", "/ch14/users3/logout",
			"/ch14/users3/register", "/ch14/users3/update", "/ch14/users3/delete"
		})
public class User3ServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length -1];
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		RequestDispatcher rd = null;
		String uid = null, pwd = null,  uname = null, email = null;
		
		switch(action) {
		case "list":
			List<User> list = dao.listUsers();
			rd = request.getRequestDispatcher("/ch14/users3/list.jsp");
			request.setAttribute("userList", list);
			rd.forward(request, response);
			break;
		case "login":
			uid = request.getParameter("uid");
			pwd = request.getParameter("pwd");
			User u = dao.getUserinfo(uid);
			if (u.getUid() != null) {	// uid가 존재
				
				if (BCrypt.checkpw(pwd, u.getPwd())) {		// 비밀번호 같은지 비교(암호화해서)
					// 로그인을 했을때 session에 정보 저장
					session.setAttribute("uid", u.getUid());
					
					// alert창에 메세지띄우고 화면이동시키기위해서 필요한경로와 메세지전달
					request.setAttribute("msg", uid + "님 환영합니다");
					request.setAttribute("url", "/jw/ch14/users3/list");
					rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
					rd.forward(request, response);
				}
				else {		
					// 비밀번호가 틀림, 로그인페이지로 다시이동
					request.setAttribute("msg", "잘못된 패스워드입니다. 다시 입력하세요");
					request.setAttribute("url", "/jw/ch14/users3/login.jsp");
					rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
					rd.forward(request, response);
				}
			} else { 			// uid가 없음
				// 회원 가입 페이지로 안내
				request.setAttribute("msg", "회원가입페이지로 이동합니다.");
				request.setAttribute("url", "/jw/ch14/users3/register.jsp");
				rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
				rd.forward(request, response);
			}
			break;
		case "logout":
			// session 정보제거(로그아웃)
			session.invalidate();
			
			request.setAttribute("msg", "로그아웃 되었습니다.");
			request.setAttribute("url", "/jw/ch14/users3/list");
			rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
			rd.forward(request, response);		
			break;
		case "register" :
			if (request.getMethod().equals("GET")) {
				response.sendRedirect("/jw/ch14/users3/register.jsp");
			}
			else {
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				uname = request.getParameter("uname");
				email = request.getParameter("email");			
				u = dao.getUserinfo(uid);
				
				if (u.getUid() != null) {			// 기존 id가 중복인경우
					request.setAttribute("msg", "중복 id가 존재합니다.");
					request.setAttribute("url", "/jw/ch14/users3/register");
					rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
					rd.forward(request, response);
				}
				else {								// id가 중복이 아닌경우
					u = new User(uid, pwd, uname, email);
					dao.registerUser(u);
					
					//코드 결과처리후(회원가입) 페이지이동
					request.setAttribute("msg", "회원가입이 완료되었습니다.");
					request.setAttribute("url", "/jw/ch14/users3/list");
					rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
					rd.forward(request, response);
				}	
			}
			break;
		case "update" :			
			// controller의 get과 post방식에서 둘다 update를 사용했기때문에 get인지 post인지 구분해준다.
			if (request.getMethod().equals("GET")) {
				uid = (String)session.getAttribute("uid");
				u = dao.getUserinfo(uid);
				request.setAttribute("uname", u.getUname());
				request.setAttribute("email", u.getEmail());
				request.setAttribute("regDate", u.getRegDate());
				rd = request.getRequestDispatcher("/ch14/users3/update.jsp");
				rd.forward(request, response);				
			} else {
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				u = dao.getUserinfo(uid);
				
				if (BCrypt.checkpw(pwd, u.getPwd())) {	// 비밀번호 일치하는지 확인
					dao.updateUser(new User(uid,pwd,uname,email));
					
					request.setAttribute("msg", u.getUid() + "님 정보가 수정되었습니다.");
					request.setAttribute("url", "/jw/ch14/users3/list");
					rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
					rd.forward(request, response);
				}
				else { 									// 비밀번호 틀렸을때
					request.setAttribute("msg", "패스워드가 일치하지 않습니다. 패스워드를 확인해주세요.");
					request.setAttribute("url", "/jw/ch14/users3/update");
					rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
					rd.forward(request, response);

				}
			}
			break;
		case "delete" :
			uid = request.getParameter("uid");
			dao.deleteUser(uid);
			
			request.setAttribute("msg", uid + "님의 데이터가 삭제되었습니다.");
			request.setAttribute("url", "/jw/ch14/users3/list");
			rd = request.getRequestDispatcher("/ch14/users3/alertMsg.jsp");
			rd.forward(request, response);
			break;
		default :
			System.out.println("잘못된경로");
			break;
		}
	}

}
