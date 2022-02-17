package com.example.test2.Service.PrimaryService;

import com.example.test2.POJO.Admin;
import com.example.test2.Util.PageInfo;
import org.apache.ibatis.annotations.Param;

public interface AdminService {

    void addAdmin(Admin admin);

    Admin login(String username,String password);

    void changePassword(Long id,String newPassword,String oldPassword);

    void removeAdminById(Long id);

    void changeGroupIdById(Long id,Long group_id);

    PageInfo<Admin> queryAdminByInGroupId(Integer currentPage, Integer pageSize, Long id, Long group_id);

    PageInfo<Admin> queryAdminByNotInGroupId(Integer currentPage, Integer pageSize, Long id, Long group_id);

    PageInfo<Admin> queryByAll(Integer currentPage, Integer pageSize, Long id);
}
