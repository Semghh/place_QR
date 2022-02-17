package com.example.test2.Mapper.Primary;

import com.example.test2.POJO.AdminGroupStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminGroupMapper {

    int insertAdminGroup(AdminGroupStore adminGroup);

    int deleteAdminGroupById(@Param("id") Long id);

    AdminGroupStore selectAdminGroupById(@Param("id") Long id);

    int updateAdminGroupById(AdminGroupStore adminGroupStore);

    Long count(@Param("id") Long id);

    List<AdminGroupStore> selectByAll(@Param("currentPage") Integer currentPage,
                                      @Param("pageSize") Integer pageSize,
                                      @Param("id") Long id);

}
