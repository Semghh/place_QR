package com.example.test2.Controllers;

import com.example.test2.POJO.LogAdminLogin;
import com.example.test2.Service.PrimaryService.LogAdminLoginService;
import com.example.test2.Util.JsonResult;
import com.example.test2.Util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/logAdmin")
public class LogAdminController extends BaseController{

    private final LogAdminLoginService logAdminLoginService;

    @Autowired
    public LogAdminController(LogAdminLoginService logAdminLoginService) {
        this.logAdminLoginService = logAdminLoginService;
    }

    @GetMapping(value = "/logLoginlistPage")
    public JsonResult<HashMap<String,PageInfo<LogAdminLogin>>>
    logLoginlistPage(@RequestParam(value = "currentPage",
            required = false,defaultValue = "1")
            Integer currentPage,
            Long admin_id){
        PageInfo<LogAdminLogin> pageInfo=logAdminLoginService.logLoginlistPage(currentPage,admin_id);
        HashMap<String,PageInfo<LogAdminLogin>> hashMap=new HashMap<>();
        hashMap.put("pageInfo",pageInfo);
        return new JsonResult<>(OK,hashMap);
    }

}
