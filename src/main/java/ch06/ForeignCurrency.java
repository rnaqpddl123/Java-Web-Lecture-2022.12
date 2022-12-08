package ch06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForeignCurrency
 */
@WebServlet("/ch06/currency")
public class ForeignCurrency extends HttpServlet {
	private static final int DEFAULT_WON = 100000;
	private static final String[] FOREIGN_CURRENCY = {"달러(USD)", "유로(EUR)", "엔(JPY)", "위안(CNY)"};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String won_ = request.getParameter("won");
		String currency_ = request.getParameter("currency");
		
		int won = DEFAULT_WON;
		try {
			won = Integer.parseInt(won_); 
		} catch (Exception e) { }
		
		double[] currencyRate = {1320.2, 1386.61, 9.63, 189.31};
		int currencyIndex = Integer.parseInt(currency_);
		
		double exchangeAmount = won/currencyRate[currencyIndex];
		String data = String.format("%,d", won) + "원 →→→ ";
		data += String.format("%,.2f %s", exchangeAmount, FOREIGN_CURRENCY[currencyIndex]);
		System.out.println(won + "원 ==> " + String.format("%,.2f %s", exchangeAmount, FOREIGN_CURRENCY[currencyIndex]));
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html lang = \"ko\">");
		out.print("<head>");
		out.print("	<meta charset=\"UTF-8\">");
		out.print("	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("	<title>Insert title here</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("	<h1>환율 계산기</h1>");
		out.print("	<hr>");	
		out.print("	<h3>" + data + "</h3>");
		out.print("	<br>");	
		out.print(" <button onclick=\"location.href='/jw/ch06/ForeignCurrency.html'\">재실행</button>");
		out.print("</body>");
		out.print("</html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
