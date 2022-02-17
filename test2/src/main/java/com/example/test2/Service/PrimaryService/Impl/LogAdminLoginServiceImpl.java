package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.LogAdminLoginMapper;
import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.POJO.LogAdminOperation;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.PrimaryService.LogAdminLoginService;
import com.example.test2.Util.PageInfo;
import com.example.test2.Util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("LogAdminLoginService")
public class LogAdminLoginServiceImpl implements LogAdminLoginService {

    private final LogAdminLoginMapper logAdminLoginMapper;

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Autowired
    public LogAdminLoginServiceImpl(LogAdminLoginMapper logAdminLoginMapper) {
        this.logAdminLoginMapper = logAdminLoginMapper;
    }

    @Override
    public void addLogAdminLogin(LogAdminLogin adminLogin) {
        adminLogin.setLogin_time(System.currentTimeMillis());
        int rows=logAdminLoginMapper.insertLogAdminLogin(adminLogin);
        if(rows!=1){
            throw new InsertException("插入数据时未知异常");
        }
    }

    @Override
    public PageInfo<LogAdminLogin> logLoginlistPage(Integer currentPage,Integer pageSize, Long admin_id) {
        if(pageSize==null){
            pageSize=this.pageSize;
        }
        String queryMethodName="selectByAll";
        String countMethodName="count";
        Object[] queryParams=new Object[3];
        Object[] countParams=new Object[1];
        queryParams[0]=currentPage;
        queryParams[1]=pageSize;
        queryParams[2]=admin_id;
        countParams[0]=admin_id;
        Class<?>[] queryCls=new Class[3];
        Class<?>[] countCls=new Class[1];
        queryCls[0]=Integer.class;
        queryCls[1]=Integer.class;
        queryCls[2]=Long.class;
        countCls[0]=Long.class;
        PageInfo<LogAdminLogin> pageInfo= PageUtil.listPage(logAdminLoginMapper,queryMethodName,queryCls,
                queryParams,countMethodName,countCls,countParams);
        return pageInfo;
    }
}
