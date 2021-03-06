package com.example.test2.Service;

import com.example.test2.POJO.Admin;
import com.example.test2.Service.PrimaryService.AdminService;
import com.example.test2.Util.PageInfo;
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

    @Test
    public void queryAdminByInGroupId(){
        PageInfo<Admin> pageInfo=adminService.queryAdminByInGroupId(1,null,null,1L);
        System.out.println(pageInfo);
    }

    @Test
    public void queryAdminByNotInGroupId(){
        PageInfo<Admin> pageInfo=adminService.queryAdminByNotInGroupId(1,null,null,1L);
        System.out.println(pageInfo);
    }

    @Test
    public void queryByAll(){
        PageInfo<Admin> pageInfo=adminService.queryByAll(1,null,2L);
        System.out.println(pageInfo);
    }
}
