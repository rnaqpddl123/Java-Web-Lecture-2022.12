package ch10;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class LoginListener
 *
 */
//@WebListener
public class LoginListener implements HttpSessionAttributeListener {
	static int total_user = 0;
	static int tmp = 0;
	
    public LoginListener() {
        // TODO Auto-generated constructor stub
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	total_user++;
    	// ch09/user/list로 연습했는데 session에 uid,uname두개를 집어넣어서 두개씩 늘어나서 나누기 2를해줌
    	// session의 내용은 못보고 생겼다,사라졋다만 볼수있음
    	if (total_user % 2 == 0)
    		System.out.println("로그인 사용자 수: " +total_user/2);
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	total_user--;
    	if (total_user % 2 == 0)
    		System.out.println("로그인 사용자 수: " + total_user/2);
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	System.out.println("attributeReplaced()");
    }
	
}
