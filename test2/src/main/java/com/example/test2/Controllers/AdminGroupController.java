package com.example.test2.Controllers;

import com.example.test2.Controllers.Exception.ParamIsNullException;
import com.example.test2.POJO.AdminGroup;
import com.example.test2.POJO.AdminGroupStore;
import com.example.test2.Service.PrimaryService.AdminGroupService;
import com.example.test2.Util.JsonResult;
import com.example.test2.Util.ParameterUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/AdminGroup")
public class AdminGroupController extends BaseController{

    @Resource
    private AdminGroupService adminGroupService;

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
}
