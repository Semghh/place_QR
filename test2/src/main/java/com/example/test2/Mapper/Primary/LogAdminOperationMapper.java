package com.example.test2.Mapper.Primary;

import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.POJO.LogAdminOperation;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface LogAdminOperationMapper {

    int insertLogAdminOperation(LogAdminOperation logAdminOperation);

    Long count(@Param("admin_id") Long admin_id);

    ArrayList<LogAdminOperation> selectByAll(@Param("currentPage") Integer currentPage,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("admin_id") Long admin_id);

}
