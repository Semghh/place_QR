package com.example.test2.Service.PrimaryService;

import com.example.test2.POJO.AdminGroup;
import com.example.test2.POJO.AdminGroupStore;
import com.example.test2.Util.PageInfo;

public interface AdminGroupService {

    void addAdminGroup(AdminGroupStore adminGroup);

    void removeAdminGroupById(Long id);

    AdminGroup resultMenu(Long id);

    PageInfo<AdminGroupStore> queryByAll(Integer currentPage, Integer pageSize, Long id);

    void changeAdminGroupById(AdminGroupStore adminGroupStore);

}
