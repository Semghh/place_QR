package com.example.test2.Service;

import com.example.test2.POJO.Admin;
import com.example.test2.Service.PrimaryService.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceTests {

    @Autowired
    private AdminService adminService;

    @Test
    public void register(){
        Admin admin=new Admin();
        admin.setUsername("af2");
        admin.setPassword("12345");
        admin.setGroup_id(1L);
        adminService.addAdmin(admin);
    }

    @Test
    public void login(){
        System.out.println(adminService.login("af","12345"));
    }

    @Test
    public void changePassword(){
        adminService.changePassword(1L,"123","123456");
    }

    @Test
    public void removeAdminById(){
        adminService.removeAdminById(3L);
    }
}
