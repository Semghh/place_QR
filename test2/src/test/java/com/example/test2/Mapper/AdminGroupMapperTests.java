package com.example.test2.Mapper;

import com.example.test2.Mapper.Primary.AdminGroupMapper;
import com.example.test2.POJO.AdminGroupStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminGroupMapperTests {

    @Autowired
    private AdminGroupMapper adminGroupMapper;

    @Test
    public void insertAdminGroup(){
        AdminGroupStore adminGroup=new AdminGroupStore();
        adminGroup.setGroup_name("管理组3");
        adminGroup.setAuthority_collection("233");
        adminGroup.setComment("111");
        System.out.println(adminGroupMapper.insertAdminGroup(adminGroup));
    }

    @Test
    public void deleteAdminGroupById(){
        System.out.println(adminGroupMapper.deleteAdminGroupById(3L));
    }

    @Test
    public void selectAdminGroupById(){
        System.out.println(adminGroupMapper.selectAdminGroupById(1L));
    }

    @Test
    public void updateAdminGroupById(){
        AdminGroupStore adminGroupStore=new AdminGroupStore();
        adminGroupStore.setId(1L);
        adminGroupStore.setGroup_name("aaa");
        adminGroupStore.setAuthority_collection("4");
        adminGroupStore.setComment("啊哈哈哈");
        System.out.println(adminGroupMapper.updateAdminGroupById(adminGroupStore));
    }


}
