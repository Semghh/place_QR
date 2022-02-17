package com.example.test2.Mapper.Primary;

import com.example.test2.POJO.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface AdminMapper {

    int insertAdmin(Admin admin);

    Admin selectAdminByUsername(@Param("username") String username);

    int updatePasswordById(@Param("id") Long id,@Param("password") String password);

    int updateGroupIdById(@Param("id") Long id,@Param("group_id") Long group_id);

    Admin selectAdminById(@Param("id") Long id);

    int deleteAdminById(@Param("id") Long id);

    Long countInGroupId(@Param("id") Long id,@Param("group_id") Long group_id);

    Long countNotInGroupId(@Param("id") Long id,@Param("group_id") Long group_id);

    Long count(@Param("id") Long id);

    ArrayList<Admin> selectAdminByInGroupId(@Param("currentPage") Integer currentPage,
                                            @Param("pageSize") Integer pageSize,
                                            @Param("id") Long id,
                                            @Param("group_id") Long group_id);

    ArrayList<Admin> selectAdminByNotInGroupId(@Param("currentPage") Integer currentPage,
                                               @Param("pageSize") Integer pageSize,
                                               @Param("id") Long id,
                                               @Param("group_id") Long group_id);

    ArrayList<Admin> selectByAll(@Param("currentPage") Integer currentPage,
                                 @Param("pageSize") Integer pageSize,
                                 @Param("id") Long id);

}
