package hd.erp.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;




@Aspect
@Component
//@Slf4j //�Һ� �Ф�
public class AopConfig {

	
	private static final Logger log = LoggerFactory.getLogger(AopConfig.class);

	
	
	//���� model�� list�� ���ٲٳ� ;;
	@After("execution(* hd.erp.controller.BoardController.boarddetail(*,*))")
	public void dosomething(JoinPoint joinpoint) {
		log.info("asdf");
		Object[] args =joinpoint.getArgs();
		for (Object e : args) {
			System.out.println("aop"+e.toString());
		}
		//Model board = (Model) args[1];
		
		
		
	}
}
