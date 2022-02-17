package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.LogAdminOperationMapper;
import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.POJO.LogAdminOperation;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.PrimaryService.LogAdminOperationService;
import com.example.test2.Util.PageInfo;
import com.example.test2.Util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "LogAdminOperationService")
public class LogAdminOperationServiceImpl implements LogAdminOperationService {

    private final LogAdminOperationMapper logAdminOperationMapper;

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Autowired
    public LogAdminOperationServiceImpl(LogAdminOperationMapper logAdminOperationMapper) {
        this.logAdminOperationMapper = logAdminOperationMapper;
    }

    @Override
    public void addLogAdminOperation(LogAdminOperation logAdminOperation) {
        int rows=logAdminOperationMapper.insertLogAdminOperation(logAdminOperation);
        if(rows!=1){
            throw new InsertException("插入数据时未知异常");
        }
    }

    @Override
    public PageInfo<LogAdminOperation> logOperationlistPage(int currentPage,Integer pageSize, Long admin_id) {
        if(pageSize==null){
            pageSize=this.pageSize;
        }
        String queryMethodName="selectByAll";
        String countMethodName="count";
        Object[] queryParams=new Object[3];
        queryParams[0]=currentPage;
        queryParams[1]=pageSize;
        queryParams[2]=admin_id;
        Object[] countParams=new Object[1];
        countParams[0]=admin_id;
        Class<?>[] queryCls=new Class[3];
        Class<?>[] countCls=new Class[1];
        queryCls[0]=Integer.class;
        queryCls[1]=Integer.class;
        queryCls[2]=Long.class;
        countCls[0]=Long.class;
        PageInfo<LogAdminOperation> pageInfo= PageUtil.listPage(logAdminOperationMapper,queryMethodName,queryCls,
                                     queryParams,countMethodName,countCls,countParams);
        return pageInfo;
    }
}
