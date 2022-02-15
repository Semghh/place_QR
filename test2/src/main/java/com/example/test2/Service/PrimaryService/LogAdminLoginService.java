package com.example.test2.Service.PrimaryService;

import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.Util.PageInfo;

import java.util.ArrayList;

public interface LogAdminLoginService {

    void addLogAdminLogin(LogAdminLogin adminLogin);

    PageInfo<LogAdminLogin> logLoginlistPage(int currentPage, Long admin_id);
}
