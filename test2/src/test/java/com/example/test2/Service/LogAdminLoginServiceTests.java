package com.example.test2.Service;

import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.Service.PrimaryService.LogAdminLoginService;
import com.example.test2.Util.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogAdminLoginServiceTests {

    @Autowired
    private LogAdminLoginService adminLoginService;

    @Test
    public void addLogAdminLogin(){
        LogAdminLogin logAdminLogin=new LogAdminLogin();
        logAdminLogin.setAdmin_id(5L);
        logAdminLogin.setUsername("af4");
        adminLoginService.addLogAdminLogin(logAdminLogin);
    }

    @Test
    public void listPage(){
        PageInfo<LogAdminLogin> pageInfo=adminLoginService.logLoginlistPage(3,4,4L);
        System.out.println(pageInfo);
        List<LogAdminLogin> list=pageInfo.getList();
        list.forEach(o1-> System.out.println(o1));
    }
}
