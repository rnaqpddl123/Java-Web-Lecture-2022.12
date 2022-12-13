package ch09.users;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 비밀번호가 암호화가 안돼있어서 비밀번호를 암호화처리해서 저장해줄려고 만들었음
 */
@WebServlet("/ch09/updatepwdss")
public class PasswordBCrypt extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDao dao = new UserDao();
		List<User> list = dao.listUsers();
		LocalDate now = LocalDate.now();
		dao.updateUser(new User("brian", "brian", "브라이언", "brian@mysql.com", now));
		dao.updateUser(new User("java", "java", "자바", "java@mysql.com", now));
		dao.updateUser(new User("kevin", "kevin", "케빈", "kevin@mysql.com", now));
		dao.updateUser(new User("maria", "maria", "마리아", "maria@mysql.com", now));
		dao.updateUser(new User("admin", "admin", "관리자", "admin@mysql.com", now));
	}

}
