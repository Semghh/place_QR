package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.LogAdminLoginMapper;
import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.PrimaryService.LogAdminLoginService;
import com.example.test2.Util.PageInfo;
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
    public PageInfo<LogAdminLogin> logLoginlistPage(int currentPage, Long admin_id) {
        ArrayList<LogAdminLogin> list=logAdminLoginMapper.selectByAll((currentPage-1)*pageSize,pageSize,admin_id);
        PageInfo<LogAdminLogin> pageInfo=new PageInfo<>();
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(currentPage);
        Long totalSize=logAdminLoginMapper.count(admin_id);
        Integer totalPage= Math.toIntExact(totalSize % pageSize == 0 ? (totalSize / pageSize) : ((totalSize / pageSize) + 1));
        pageInfo.setTotalPage(totalPage);
        pageInfo.setTotalSize(totalSize);
        return pageInfo;
    }
}
