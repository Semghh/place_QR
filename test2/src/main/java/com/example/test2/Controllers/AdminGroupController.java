package com.example.test2.Controllers;

import com.example.test2.Controllers.Exception.ParamIsNullException;
import com.example.test2.POJO.Admin;
import com.example.test2.POJO.AdminGroup;
import com.example.test2.POJO.AdminGroupStore;
import com.example.test2.Service.PrimaryService.AdminGroupService;
import com.example.test2.Service.PrimaryService.AdminService;
import com.example.test2.Util.JsonResult;
import com.example.test2.Util.PageInfo;
import com.example.test2.Util.ParameterUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/adminGroup")
public class AdminGroupController extends BaseController{

    @Resource
    private AdminGroupService adminGroupService;

    @Resource
    private AdminService adminService;

    @PostMapping(value = "/addAdminGroup")
    public JsonResult<Void> addAdminGroup(AdminGroupStore adminGroupStore){
        adminGroupStore.setId(-1L);
        if(!ParameterUtil.parameterCheck(adminGroupStore)){
            throw new ParamIsNullException("指定参数不能为空");
        }
        adminGroupService.addAdminGroup(adminGroupStore);
        return new JsonResult<>(OK);
    }

    @GetMapping(value = "/resultMenu")
    public JsonResult<HashMap<String,AdminGroup>> resultMenu(HttpSession session){
        Long id= (Long) session.getAttribute("group_id");
        AdminGroup adminGroup=adminGroupService.resultMenu(id);
        HashMap<String,AdminGroup> hashMap=new HashMap<>();
        hashMap.put("adminGroup",adminGroup);
        return new JsonResult<>(OK,hashMap);
    }

    @GetMapping(value = "/queryByAll")
    public JsonResult<HashMap<String, PageInfo<AdminGroupStore>>>
    queryByAll(@RequestParam(value = "currentPage",
            required = false,defaultValue = "1")
            Integer currentPage,
            @RequestParam(value = "pageSize",required = false)
            Integer pageSize,
            @RequestParam(value = "id",required = false)
            Long id){
        PageInfo<AdminGroupStore> pageInfo=adminGroupService.queryByAll(currentPage, pageSize, id);
        HashMap<String,PageInfo<AdminGroupStore>> hashMap=new HashMap<>();
        hashMap.put("pageInfo",pageInfo);
        return new JsonResult<>(OK,hashMap);
    }

    @GetMapping(value = "/queryAdminByInGroupId")
    public JsonResult<HashMap<String, PageInfo<Admin>>>
            queryAdminByInGroupId(@RequestParam(value = "currentPage",
            required = false,defaultValue = "1")
            Integer currentPage,
            @RequestParam(value = "pageSize",required = false)
            Integer pageSize,
            @RequestParam(value = "admin_id",required = false)
            Long admin_id,
            @RequestParam(value = "id")
            Long id){
        PageInfo<Admin> pageInfo=adminService.queryAdminByInGroupId(currentPage, pageSize, admin_id, id);
        HashMap<String,PageInfo<Admin>> hashMap=new HashMap<>();
        hashMap.put("pageInfo",pageInfo);
        return new JsonResult<>(OK,hashMap);
    }

    @GetMapping(value = "/queryAdminByNotInGroupId")
    public JsonResult<HashMap<String, PageInfo<Admin>>>
            queryAdminByNotInGroupId(@RequestParam(value = "currentPage",
            required = false,defaultValue = "1")
            Integer currentPage,
            @RequestParam(value = "pageSize",required = false)
            Integer pageSize,
            @RequestParam(value = "admin_id",required = false)
            Long admin_id,
            @RequestParam(value = "id")
            Long id){
        PageInfo<Admin> pageInfo=adminService.queryAdminByNotInGroupId(currentPage, pageSize, admin_id, id);
        HashMap<String,PageInfo<Admin>> hashMap=new HashMap<>();
        hashMap.put("pageInfo",pageInfo);
        return new JsonResult<>(OK,hashMap);
    }
}
