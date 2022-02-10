package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.AdminMapper;
import com.example.test2.POJO.Admin;
import com.example.test2.Service.Exception.*;
import com.example.test2.Service.PrimaryService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "AdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void register(Admin admin) {
        Admin temp=adminMapper.selectAdminByUsername(admin.getUsername());
        if(temp!=null){
            throw new UsernameDuplicatedException("用户名已被占用");
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
}