package com.example.test2.Mapper;

import com.example.test2.Mapper.Primary.AdminMapper;
import com.example.test2.POJO.Admin;
import com.example.test2.Util.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminMapperTests {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void insertAdmin(){
        Admin admin=new Admin();
        admin.setUsername("af1");
        admin.setPassword("123");
        admin.setGroup_id(1L);
        System.out.println(adminMapper.insertAdmin(admin));
    }

    @Test
    public void selectAdminByUsername(){
        System.out.println(adminMapper.selectAdminByUsername("af"));
    }

    @Test
    public void updatePasswordById(){
        System.out.println(adminMapper.updatePasswordById(2L,"12345"));
    }

    @Test
    public void selectAdminById(){
        System.out.println(adminMapper.selectAdminById(1L));
    }

    @Test
    public void deleteAdminById(){
        System.out.println(adminMapper.deleteAdminById(2L));
    }

}
