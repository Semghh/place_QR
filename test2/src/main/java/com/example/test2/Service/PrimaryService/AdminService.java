package com.example.test2.Service.PrimaryService;

import com.example.test2.POJO.Admin;

public interface AdminService {

    void register(Admin admin);

    Admin login(String username,String password);

    void changePassword(Long id,String newPassword,String oldPassword);

}