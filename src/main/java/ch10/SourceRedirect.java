package ch10;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SourceRefresh
 */
@WebServlet("/ch10/src")
public class SourceRedirect extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SourceRedirect doget()");
		
		String msg = "SourceRedirect, 한글 확인용 메세지";
		
		response.sendRedirect("/jw/ch10/filterTest?msg=" + msg);
	}

}
