package hd.erp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;




@Controller
//@Slf4j ·Òº¹ ¤Ð¤Ð
public class ExceptionController implements ErrorController {

	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest req) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) {
			
			int statusCode = Integer.valueOf(status.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error/404page";
			}
//			if(statusCode == HttpStatus.FORBIDDEN.value()) {
//				return "";
//			}
//			if(statusCode == HttpStatus.FORBIDDEN.value()) {
//			return "";
//		}
		}
		return "error";
	}
	
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}
