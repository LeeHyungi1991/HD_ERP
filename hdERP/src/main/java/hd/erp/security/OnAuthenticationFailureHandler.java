package hd.erp.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
@Configuration
public class OnAuthenticationFailureHandler implements AuthenticationFailureHandler {

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

//		String username = request.getParameter("loginid");
//        String password = request.getParameter("loginpw");
        
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errormsg = exception.getMessage();
        System.out.println("username"+username);
        System.out.println("password"+password);
        System.out.println("errormsg"+errormsg);
        request.setAttribute("loginid", username);
        request.setAttribute("loginpw", password);
        request.setAttribute("errormsg", errormsg);
        
 
        
        	System.out.println("로그인 실패");
//        request.getRequestDispatcher("").forward(request, response);
//        	RequestDispatcher dispatcher = request.getRequestDispatcher("/loginfail");
//        	dispatcher.forward(request, response);

        	
        	response.sendRedirect("/loginfail?error=LoginFail");



		
	}

}
