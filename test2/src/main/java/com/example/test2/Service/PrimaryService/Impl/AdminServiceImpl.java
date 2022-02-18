package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.AdminGroupMapper;
import com.example.test2.Mapper.Primary.AdminMapper;
import com.example.test2.POJO.Admin;
import com.example.test2.POJO.AdminGroupStore;
import com.example.test2.Service.Exception.*;
import com.example.test2.Service.PrimaryService.AdminService;
import com.example.test2.Util.PageInfo;
import com.example.test2.Util.PageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "AdminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private AdminGroupMapper adminGroupMapper;

    @Value("${page.pageSize}")
    private Integer pageSize;

    @Override
    public void addAdmin(Admin admin) {
        Admin temp=adminMapper.selectAdminByUsername(admin.getUsername());
        if(temp!=null){
            throw new UsernameDuplicatedException("用户名已被占用");
        }
        AdminGroupStore adminGroup =adminGroupMapper.selectAdminGroupById(admin.getGroup_id());
        if(adminGroup==null){
            throw new GroupNotFoundException("分组数据不存在");
        }
        Integer rows=adminMapper.insertAdmin(admin);
        if(rows!=1){
            throw new InsertException("用户注册未知异常");
        }
    }

    @Override
    public Admin login(String username, String password) {
        Admin temp=adminMapper.selectAdminByUsername(username);
        if(temp==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        if(!temp.getPassword().equals(password)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        return temp;
    }

    @Override
    public void changePassword(Long id, String newPassword,String oldPassword) {
        Admin temp=adminMapper.selectAdminById(id);
        if(temp==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        if(!temp.getPassword().equals(oldPassword)){
            throw new PasswordNotMatchException("密码错误");
        }
        Integer rows=adminMapper.updatePasswordById(id, newPassword);
        if(rows!=1){
            throw new UpdateException("更新数据未知错误");
        }
    }

    @Override
    public void removeAdminById(Long id) {
        Admin temp=adminMapper.selectAdminById(id);
        if(temp==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        int rows=adminMapper.deleteAdminById(id);
        if(rows!=1){
            throw new DeleteException("删除未知异常");
        }
    }

    @Override
    public void changeGroupIdById(Long id, Long group_id) {
        int rows=adminMapper.updateGroupIdById(id, group_id);
        if(rows!=1){
            throw new UpdateException("更新数据时未知异常");
        }
    }

    @Override
    public PageInfo<Admin> queryAdminByInGroupId(Integer currentPage, Integer pageSize, Long id, Long group_id) {
        if(pageSize==null){
            pageSize=this.pageSize;
        }
        String queryMethodName="selectAdminByInGroupId";
        String countMethodName="countInGroupId";
        Object[] queryParams=new Object[4];
        Object[] countParams=new Object[2];
        queryParams[0]=currentPage;
        queryParams[1]=pageSize;
        queryParams[2]=id;
        queryParams[3]=group_id;
        countParams[0]=id;
        countParams[1]=group_id;
        Class<?>[] queryCls=new Class[4];
        Class<?>[] countCls=new Class[2];
        queryCls[0]=Integer.class;
        queryCls[1]=Integer.class;
        queryCls[2]=Long.class;
        queryCls[3]=Long.class;
        countCls[0]=Long.class;
        countCls[1]=Long.class;
        PageInfo<Admin> pageInfo= PageUtil.listPage(adminMapper,queryMethodName,queryCls,queryParams,countMethodName,countCls,countParams);
        return pageInfo;
    }

    @Override
    public PageInfo<Admin> queryAdminByNotInGroupId(Integer currentPage, Integer pageSize, Long id, Long group_id) {
        if(pageSize==null){
            pageSize=this.pageSize;
        }
        String queryMethodName="selectAdminByNotInGroupId";
        String countMethodName="countNotInGroupId";
        Object[] queryParams=new Object[4];
        Object[] countParams=new Object[2];
        queryParams[0]=currentPage;
        queryParams[1]=pageSize;
        queryParams[2]=id;
        queryParams[3]=group_id;
        countParams[0]=id;
        countParams[1]=group_id;
        Class<?>[] queryCls=new Class[4];
        Class<?>[] countCls=new Class[2];
        queryCls[0]=Integer.class;
        queryCls[1]=Integer.class;
        queryCls[2]=Long.class;
        queryCls[3]=Long.class;
        countCls[0]=Long.class;
        countCls[1]=Long.class;
        PageInfo<Admin> pageInfo= PageUtil.listPage(adminMapper,queryMethodName,queryCls,queryParams,countMethodName,countCls,countParams);
        return pageInfo;
    }

    @Override
    public PageInfo<Admin> queryByAll(Integer currentPage, Integer pageSize, Long id) {
        if(pageSize==null){
            pageSize=this.pageSize;
        }
        String queryMethodName="selectByAll";
        String countMethodName="count";
        Object[] queryParams=new Object[3];
        Object[] countParams=new Object[1];
        queryParams[0]=currentPage;
        queryParams[1]=pageSize;
        queryParams[2]=id;
        countParams[0]=id;
        Class<?>[] queryCls=new Class[3];
        Class<?>[] countCls=new Class[1];
        queryCls[0]=Integer.class;
        queryCls[1]=Integer.class;
        queryCls[2]=Long.class;
        countCls[0]=Long.class;
        PageInfo<Admin> pageInfo=PageUtil.listPage(adminMapper,queryMethodName,queryCls,queryParams,countMethodName,countCls,countParams);
        return pageInfo;
    }
}
