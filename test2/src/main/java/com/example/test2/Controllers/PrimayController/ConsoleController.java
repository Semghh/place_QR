package com.example.test2.Controllers.PrimayController;

import com.example.test2.Controllers.BaseController;
import com.example.test2.Service.PrimaryService.ConsoleRedisService;
import com.example.test2.Service.PrimaryService.Impl.ConsoleRedisServiceImpl;
import com.example.test2.Util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/console")
public class ConsoleController extends BaseController {

    @Resource
    private ConsoleRedisService consoleRedisService;

    @RequestMapping("/todayScan")
    public JsonResult getScanTotal(){
        Long todayScanTotal = consoleRedisService.getTodayScanTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("todayScanTotal",todayScanTotal);
        return JsonResult.getInstance(200,"获取成功",map);
    }

    @RequestMapping("/regisTotal")
    public JsonResult getRegisTotal(){
        Long registerTotal = consoleRedisService.getRegisterTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("registerTotal",registerTotal);
        return JsonResult.getInstance(200,"获取成功",map);
    }

    @RequestMapping("/riskPlace")
    public JsonResult getRiskPlaceTotal(){
        Long curRiskPlaceTotal = consoleRedisService.getCurRiskPlaceTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("curRiskPlaceTotal",curRiskPlaceTotal);
        return JsonResult.getInstance(200,"获取成功!",map);
    }




}
