package com.example.test2.Mapper.Primary;

import com.example.test2.POJO.LogAdminLogin;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface LogAdminLoginMapper {

    int insertLogAdminLogin(LogAdminLogin adminLogin);

    Long count(@Param("admin_id") Long admin_id);

    ArrayList<LogAdminLogin> selectByAll(@Param("currentPage") int currentPage,
                                         @Param("pageSize") int pageSize,
                                         @Param("admin_id") Long admin_id);
}
