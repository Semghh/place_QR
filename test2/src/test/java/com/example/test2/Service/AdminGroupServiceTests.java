package com.example.test2.Service;

import com.example.test2.POJO.AdminGroup;
import com.example.test2.POJO.AdminGroupStore;
import com.example.test2.POJO.AuthorityStoreTable;
import com.example.test2.Service.PrimaryService.AdminGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminGroupServiceTests {

    @Autowired
    private AdminGroupService adminGroupService;

    @Test
    public void addAdminGroup(){
        AdminGroupStore adminGroupStore=new AdminGroupStore();
        adminGroupStore.setGroup_name("2333");
        adminGroupStore.setComment("123");
        adminGroupStore.setAuthority_collection("1,2,3");
        adminGroupService.addAdminGroup(adminGroupStore);
    }

    @Test
    public void resultMenu(){
        AdminGroup adminGroup=adminGroupService.resultMenu(2L);
        AuthorityStoreTable[] authorityStoreTable=adminGroup.getAuthority_collection();
        for(int i=0;i<authorityStoreTable.length;i++){
            dfs(authorityStoreTable[i]);
        }
    }

    private void dfs(AuthorityStoreTable authorityStoreTable){
        if(authorityStoreTable==null){
            return;
        }
        System.out.println(authorityStoreTable.getId());
        AuthorityStoreTable[] children= authorityStoreTable.getChildren();
        if(children==null){
            return;
        }
        for(int i=0;i<children.length;i++){
            dfs(children[i]);
        }
    }
}
