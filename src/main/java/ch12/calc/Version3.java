package ch12.calc;

import java.io.IOException;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Version2
 */
@WebServlet("/ch12/calc/ver3")
public class Version3 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("eval", "0");
		RequestDispatcher rd = request.getRequestDispatcher("/ch12/calc/ver3.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		// 데이터를 stack형태로 저장하겠다는 의미
		Object obj = session.getAttribute("stack");
		Stack<Object> stack = (obj != null) ? (Stack)obj : new Stack<>();
		
		String num_ = request.getParameter("num");
		String op_ = request.getParameter("op");
		
		// 숫자를 입력했을 경우
		if (num_ != null) {		// 숫자를 입력했을 경우
			// 한자리수 이상을 계산하기 위해서
			if (stack.isEmpty()) {		// 빈계산기에 첫번째 숫자입력
				stack.push(num_);
			} else {
				String element = (String)stack.pop();
				if (isOperation(element)) {		// 숫자가 아니라 연산자(+-*/)일 경우
					stack.push(element);
					stack.push(num_);
				} else {
					num_ = element + num_;		// 숫자일 경우에 숫자를 하나로 합쳐서 다시 stack에 push 
					stack.push(num_);
				}
			}			
			session.setAttribute("stack", stack);
			request.setAttribute("eval", getEval(stack));
		} 
		// 연산자를 눌렀을 경우
		else if (op_ != null && !op_.equals("=")) {		
			if (op_.equals("C") && !stack.isEmpty()) {		// stack이 비어있지않고 C를 눌렀을때 (입력값이 있는상태에서 제거를눌렀을때)
				String s = (String) stack.pop();	
				if (!isOperation(s) && s.length() >= 2) {	// 마지막 입력값이 숫자일경우
						s = s.substring(0, s.length()-1);
						stack.push(s);
				}
			} else if (!stack.isEmpty()){				//스택이 비어있지않다면 연산자 push
				String confirm = (String) stack.peek();		
				if (isOperation(confirm))				// 마지막 스택이 연산자면은 연산자를 교체
					stack.pop();
				stack.push(op_);
			} 
			session.setAttribute("stack", stack);
			request.setAttribute("eval", getEval(stack));
		} 
		// "="를 눌렀을경우
		else {		
			String confirm = (String) stack.peek();	
			if (!stack.isEmpty() && !isOperation(confirm)) {	 	//입력값이 있고 마지막 입력값이 부호가 아닐때
				int result = 0;
				// stack은 나중에 입력한걸 먼저뺀다는걸 주의해야함
				int num2 = Integer.parseInt((String)stack.pop());
				String op = (String) stack.pop();
				int num1 = Integer.parseInt((String)stack.pop());
				switch(op) {
				case "+" : result = num1+num2; break;
				case "-" : result = num1-num2; break;
				case "*" : result = num1*num2; break;
				case "/" : result = num1/num2; break;
				default: result = Integer.MAX_VALUE; break;
				}	
				session.removeAttribute("stack");
				request.setAttribute("eval", result);
			} else {	// 입력값이 없을때
				request.setAttribute("eval", getEval(stack));
			}		
		}
		rd = request.getRequestDispatcher("/ch12/calc/ver3.jsp");
		rd.forward(request, response);
	}
	
	// 세션에 저장해 놓은 입력값들을 계산해서 eval(최종결과)를 리턴
	String getEval(Stack<Object> stack) {
		String eval = "";
		if (stack.isEmpty())
			return eval;
		for (Object s : stack)
			eval += (String) s + " ";
		return eval;
	}
	
	//  s가 연산자(+-*/)일경우에 true 
	boolean isOperation(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}

}
