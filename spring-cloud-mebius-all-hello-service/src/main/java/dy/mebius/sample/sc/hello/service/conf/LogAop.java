package dy.mebius.sample.sc.hello.service.conf;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {

    @Pointcut("execution(* dy.mebius.sample.sc.hello.service.controller..*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        sb.append("------- LogAop ---------\n");
        sb.append("方法名:" + joinPoint.getSignature().getName());
        sb.append("\n----------------------\n");

        System.out.println(sb.toString());
    }

}
