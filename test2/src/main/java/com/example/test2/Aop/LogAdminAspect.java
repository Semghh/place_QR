package com.example.test2.Aop;

import com.example.test2.POJO.Admin;
import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.POJO.LogAdminOperation;
import com.example.test2.Service.PrimaryService.LogAdminLoginService;
import com.example.test2.Service.PrimaryService.LogAdminOperationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
@Aspect
public class LogAdminAspect {

    private final LogAdminLoginService logAdminLoginService;
    private final LogAdminOperationService logAdminOperationService;

    @Value(value = "${log.logCode.insertCode}")
    private Integer INSERTCODE;

    @Value(value = "${log.logCode.deleteCode}")
    private Integer DELETECODE;

    @Value(value = "${log.logCode.updateCode}")
    private Integer UPDATECODE;


    @Autowired
    public LogAdminAspect(LogAdminLoginService logAdminLoginService, LogAdminOperationService logAdminOperationService) {
        this.logAdminLoginService = logAdminLoginService;
        this.logAdminOperationService = logAdminOperationService;
    }

    @Pointcut("execution(public com.example.test2.POJO.Admin " +
              "com.example.test2.Service.PrimaryService." +
              "Impl.AdminServiceImpl.login(String,String))")
    public void logLogin(){}

    @Pointcut("execution(public void com.example.test2.Service.PrimaryService.Impl.AdminServiceImpl.add*(..)) || " +
            "execution(public void com.example.test2.Service.PrimaryService.Impl.AdminGroupServiceImpl.add*(..)) || " +
            "execution(public void com.example.test2.Service.PrimaryService.Impl.AuthorityStoreTableServiceImpl.add*(..))")
    public void logAddOperation(){}

    @Pointcut("execution(public void com.example.test2.Service.PrimaryService.Impl.AdminServiceImpl.change*(..)) || " +
            "execution(public void com.example.test2.Service.PrimaryService.Impl.AdminGroupServiceImpl.change*(..)) ")
    public void logChangeOperation(){}

    @Pointcut("execution(public void com.example.test2.Service.PrimaryService.Impl.AdminServiceImpl.remove*(..)) || " +
            "execution(public void com.example.test2.Service.PrimaryService.Impl.AdminGroupServiceImpl.remove*(..)) ")
    public void logRemoveOperation(){}

    @AfterReturning(value = "logLogin()",returning = "res")
    public void logAdminLogin(Admin res){
        LogAdminLogin logAdminLogin=new LogAdminLogin();
        logAdminLogin.setAdmin_id(res.getId());
        logAdminLogin.setUsername(res.getUsername());
        logAdminLoginService.addLogAdminLogin(logAdminLogin);
    }

    @AfterReturning("logAddOperation()")
    public void logAdminAddOperation(JoinPoint joinPoint){
        log(joinPoint,INSERTCODE);
    }

    @AfterReturning("logChangeOperation()")
    public void logAdminChangeOperation(JoinPoint joinPoint){
        log(joinPoint,UPDATECODE);
    }

    @AfterReturning("logRemoveOperation()")
    public void logAdminRemoveOperation(JoinPoint joinPoint){
        log(joinPoint,DELETECODE);
    }

    private void log(JoinPoint joinPoint,Integer logCode){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        LogAdminOperation logAdminOperation=new LogAdminOperation();
        Long admin_id= (Long) session.getAttribute("id");
        String username= (String) session.getAttribute("username");
        String methodRoot = joinPoint.getSignature().getDeclaringTypeName();
        String method = methodRoot + "." + joinPoint.getSignature().getName();
        logAdminOperation.setAdmin_id(Long.valueOf(admin_id));
        logAdminOperation.setMethod_path(method);
        logAdminOperation.setOperation_time(System.currentTimeMillis());
        logAdminOperation.setType(logCode);
        logAdminOperation.setUsername(username);
        logAdminOperationService.addLogAdminOperation(logAdminOperation);
    }
}
