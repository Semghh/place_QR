package com.example.test2.Controllers.PrimayController;

import com.example.test2.Controllers.BaseController;
import com.example.test2.POJO.Area;
import com.example.test2.POJO.JsonArea;
import com.example.test2.Service.PrimaryService.AreaStoreService;
import com.example.test2.Service.PrimaryService.Impl.AreaStoreServiceImpl;
import com.example.test2.Util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import sun.awt.image.ImageWatched;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.List;
@RestController
@RequestMapping("/area")
public class AreaController extends BaseController {

    @Resource
    private AreaStoreService areaStoreService;

    @Resource
    private AreaStoreServiceImpl.AreaTreeUtil util;


    @GetMapping("/cityList")
    public JsonResult getCityList(){
        List<Area> maxArea = util.getMaxArea();
        LinkedList<JsonArea> data = new LinkedList<>();
        maxArea.forEach(x->{
            data.add(new JsonArea(x.getId(),x.getName()));
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("cityList",data);
        return JsonResult.getInstance(200,"查询成功!",map);
    }

    @GetMapping("/childArea")
    public JsonResult getChildArea(@RequestParam("fatherId")Long fatherId){
        List<Area> nodes = util.getNodeByFatherId(fatherId);

        LinkedList<JsonArea> data = new LinkedList<>();
        nodes.forEach(x->{
            data.add(new JsonArea(x.getId(),x.getName()));
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("childArea",data);
        return JsonResult.getInstance(200,"查询成功!",map);
    }


}
