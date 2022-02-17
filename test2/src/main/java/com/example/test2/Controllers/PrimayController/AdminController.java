package com.example.test2.Controllers.PrimayController;

import com.example.test2.Controllers.BaseController;
import com.example.test2.Controllers.Exception.ParamIsNullException;
import com.example.test2.POJO.Admin;
import com.example.test2.POJO.AdminGroupStore;
import com.example.test2.Service.PrimaryService.AdminService;
import com.example.test2.Util.JsonResult;
import com.example.test2.Util.PageInfo;
import com.example.test2.Util.ParameterUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Resource
    private AdminService adminService;

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
            throw new ParamIsNullException("指定参数不能为空");
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
            throw new ParamIsNullException("指定参数不能为空");
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

    @GetMapping(value = "/queryByAll")
    public JsonResult<HashMap<String, PageInfo<Admin>>>
            queryByAll(@RequestParam(value = "currentPage",
            required = false,defaultValue = "1")
            Integer currentPage,
            @RequestParam(value = "pageSize",required = false)
            Integer pageSize,
            @RequestParam(value = "id",required = false)
            Long id){
        PageInfo<Admin> pageInfo=adminService.queryByAll(currentPage, pageSize, id);
        HashMap<String,PageInfo<Admin>> hashMap=new HashMap<>();
        hashMap.put("pageInfo",pageInfo);
        return new JsonResult<>(OK,hashMap);
    }

}
