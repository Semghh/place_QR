package com.example.test2.Controllers.PrimayController;

import com.example.test2.Controllers.BaseController;
import com.example.test2.Controllers.Exception.ParameIsNullException;
import com.example.test2.POJO.Admin;
import com.example.test2.Service.PrimaryService.AdminService;
import com.example.test2.Util.JsonResult;
import com.example.test2.Util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/login")
    public JsonResult<HashMap<String,Admin>> login(String username, String password, HttpSession session){
        Admin res=adminService.login(username, password);
        session.setAttribute("id",res.getId());
        session.setAttribute("username",res.getUsername());
        session.setAttribute("group_id",res.getGroup_id());
        HashMap<String,Admin> hashMap=new HashMap<>();
        hashMap.put("admin",res);
        return new JsonResult<>(OK,hashMap);
    }

    @PostMapping(value = "/register")
    public JsonResult<Void> register(Admin admin){
        admin.setId(-1L);
        if(!ParameterUtil.parameterCheck(admin)){
            throw new ParameIsNullException("指定参数不能为空");
        }
        adminService.addAdmin(admin);
        return new JsonResult<>(OK);
    }

    @PostMapping(value = "/changePassword")
    public JsonResult<Void> changePassword(String newPassword,String oldPassword,HttpSession session){
        List params=new ArrayList();
        params.add(newPassword);
        params.add(oldPassword);
        if(!ParameterUtil.parameterCheck(params)){
            throw new ParameIsNullException("指定参数不能为空");
        }
        Long id= (Long) session.getAttribute("id");
        adminService.changePassword(id, newPassword, oldPassword);
        return new JsonResult<>(OK);
    }

    @PostMapping(value = "/removeAdminById")
    public JsonResult<Void> removeAdminById(HttpSession session){
        Long id= (Long) session.getAttribute("id");
        adminService.removeAdminById(id);
        return new JsonResult<>(OK);
    }

    @PostMapping(value = "/changeGroupIdById")
    public JsonResult<Void> changeGroupIdById(Long id,Long group_id){
        List params=new ArrayList();
        params.add(id);
        params.add(group_id);
        if(!ParameterUtil.parameterCheck(params)){
            throw new ParameIsNullException("指定参数不能为空");
        }
        adminService.changeGroupIdById(id, group_id);
        return new JsonResult<>(OK);
    }
}
