package com.example.test2.Mapper;

import com.example.test2.Mapper.Primary.LogAdminLoginMapper;
import com.example.test2.POJO.LogAdminLogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogAdminLoginMapperTests {

    @Autowired
    private LogAdminLoginMapper logAdminLoginMapper;

    @Test
    public void insertAdminGroup(){
        LogAdminLogin logAdminLogin=new LogAdminLogin();
        logAdminLogin.setAdmin_id(5L);
        logAdminLogin.setUsername("af4");
        logAdminLogin.setLogin_time(System.currentTimeMillis());
        for(int i=0;i<10;i++)
        System.out.println(logAdminLoginMapper.insertLogAdminLogin(logAdminLogin));
    }

    @Test
    public void count(){
        System.out.println(logAdminLoginMapper.count(1L));
    }

    @Test
    public void selectByAll(){
        List<LogAdminLogin> list=logAdminLoginMapper.selectByAll(0*5,5,4L);
        list.forEach(o1-> System.out.println(o1));
    }
}
