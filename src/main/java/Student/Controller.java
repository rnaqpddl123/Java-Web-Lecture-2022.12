package Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/student/*"})
public class Controller extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length -1];
		StudentDao dao = new StudentDao();
		response.setContentType("text/html; charset=utf-8");
		Student s = new Student();
		RequestDispatcher rd = null;
		
		switch(action) {
		case "page":
			List<Student> studentList = dao.studentInfo();
			rd = request.getRequestDispatcher("/studentForm/student.jsp");
			request.setAttribute("studentList", studentList);
			rd.forward(request, response);
			break;
		case "create":
			dao.createTable();
			rd = request.getRequestDispatcher("/student/page");
			rd.forward(request, response);
			break;
		case "insert":
			int sid = Integer.parseInt(request.getParameter("sid"));
			String sname = request.getParameter("sname");
			String gender = request.getParameter("gender");
			LocalDate enterYear = LocalDate.parse(request.getParameter("enterYear"));
			String deptName = request.getParameter("deptName");
			s = new Student(sid, sname, gender, enterYear, deptName);
			dao.insertStudent(s);
			rd = request.getRequestDispatcher("/student/page");
			rd.forward(request, response);
			break;
		case "select":
			dao.studentInfo();
			rd = request.getRequestDispatcher("/student/page");
			rd.forward(request, response);
			break;
		case "update":
			sid = Integer.parseInt(request.getParameter("sid"));
			sname = request.getParameter("sname");
			gender = request.getParameter("gender");
			enterYear = LocalDate.parse(request.getParameter("enterYear"));
			deptName = request.getParameter("deptName");
			s = new Student(sid, sname, gender, enterYear, deptName);
			dao.updateStudent(s);
			rd = request.getRequestDispatcher("/student/page");
			rd.forward(request, response);
			break;
		case "delete":
			sid = Integer.parseInt(request.getParameter("sid"));
			dao.delete(sid);
			
			rd = request.getRequestDispatcher("/student/page");
			rd.forward(request, response);
			break;
		default:
			break;
		
		}
		
	}
}
