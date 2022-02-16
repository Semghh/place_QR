package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.LogAdminOperationMapper;
import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.POJO.LogAdminOperation;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.PrimaryService.LogAdminOperationService;
import com.example.test2.Util.PageInfo;
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
    public PageInfo<LogAdminOperation> logOperationlistPage(int currentPage, Long admin_id) {
        ArrayList<LogAdminOperation> list=logAdminOperationMapper.selectByAll((currentPage-1)*pageSize,pageSize,admin_id);
        PageInfo<LogAdminOperation> pageInfo=new PageInfo<>();
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setCurrentPage(currentPage);
        Long totalSize=logAdminOperationMapper.count(admin_id);
        Integer totalPage= Math.toIntExact(totalSize % pageSize == 0 ? (totalSize / pageSize) : ((totalSize / pageSize) + 1));
        pageInfo.setTotalPage(totalPage);
        pageInfo.setTotalSize(totalSize);
        return pageInfo;
    }
}
