package ch08;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SourceDispatch
 */
@WebServlet("/ch08/src4")
public class SourceDispatch extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/ch08/src4 doGet()");
		
		request.setAttribute("addr", "서울시 광진구 구의동");
		// setAttribute로 저장한 데이터들을 보내고싶을때 쓰는방법
		// Dispatch를 이용한 페이지 이동 (여기서는 주소창에 이름이 바뀌지않음)
		
		// forward로 보내는 방식에서는 /jw를 붙이지 않는다.
		RequestDispatcher rd = request.getRequestDispatcher("/ch08/dst4?msg=한글");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
