package al.edu.fti.universitymanagement.uniman.core.notifications.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class FriendshipRequestAspect {

    @AfterReturning("execution(* al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl.*(..)) " +
            "|| execution(* al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl.*(..))")
    public void after(JoinPoint joinPoint){
        if (((MethodSignature)joinPoint.getSignature()).getMethod().getName().contains("save")
            || ((MethodSignature)joinPoint.getSignature()).getMethod().getName().contains("update")) {
            log.info(
                    "Executing pointcut in friendship"
            );
        }

    }
}
