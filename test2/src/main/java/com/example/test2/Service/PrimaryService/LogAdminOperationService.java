package com.example.test2.Service.PrimaryService;

import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.POJO.LogAdminOperation;
import com.example.test2.Util.PageInfo;

public interface LogAdminOperationService {

    void addLogAdminOperation(LogAdminOperation logAdminOperation);

    PageInfo<LogAdminOperation> logOperationlistPage(int currentPage,Integer pageSize, Long admin_id);

}
