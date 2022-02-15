package com.example.test2.Aop;

import com.example.test2.POJO.Admin;
import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.Service.PrimaryService.LogAdminLoginService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdminAspect {

    private final LogAdminLoginService logAdminLoginService;

    @Autowired
    public LogAdminAspect(LogAdminLoginService logAdminLoginService) {
        this.logAdminLoginService = logAdminLoginService;
    }

    @Pointcut("execution(public com.example.test2.POJO.Admin " +
              "com.example.test2.Service.PrimaryService." +
              "Impl.AdminServiceImpl.login(String,String))")
    public void logLogin(){}

    @AfterReturning(value = "logLogin()",returning = "res")
    public void logAdminLogin(Admin res){
        LogAdminLogin logAdminLogin=new LogAdminLogin();
        logAdminLogin.setAdmin_id(res.getId());
        logAdminLogin.setUsername(res.getUsername());
        logAdminLoginService.addLogAdminLogin(logAdminLogin);
    }



}
