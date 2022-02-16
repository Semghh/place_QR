package com.example.test2.Service.PrimaryService;

import com.example.test2.POJO.Admin;

public interface AdminService {

    void addAdmin(Admin admin);

    Admin login(String username,String password);

    void changePassword(Long id,String newPassword,String oldPassword);

    void removeAdminById(Long id);

    void changeGroupIdById(Long id,Long group_id);
}
