package com.example.test2.Controllers;

import com.example.test2.Controllers.Exception.ParamIsNullException;
import com.example.test2.POJO.Area;
import com.example.test2.POJO.Authority;
import com.example.test2.POJO.Result;
import com.example.test2.Service.PrimaryService.AreaStoreService;
import com.example.test2.Service.PrimaryService.AuthorityStoreTableService;
import com.example.test2.Service.PrimaryService.PlaceForNormalService;
import com.example.test2.Service.SecondaryService.QRRecordsService;
import com.example.test2.Util.JsonResult;
import com.example.test2.Util.ParamGet;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class HelloController {

    private QRRecordsService qrRecordsService;
    private PlaceForNormalService placeForNormalService;
    private AreaStoreService areaStoreService;

    @Resource
    private AuthorityStoreTableService authorityStoreTableService;

    @Resource(name = "authorityTree")
    private Authority[] authTree ;


    @Resource(name = "myArea")
    private Area area;

    @Autowired
    public HelloController(QRRecordsService qrRecordsService,PlaceForNormalService placeForNormalService,
                           AreaStoreService areaStoreService) {
        this.qrRecordsService = qrRecordsService;
        this.placeForNormalService = placeForNormalService;
        this.areaStoreService = areaStoreService;
    }

    @RequestMapping("/Secondary")
    public String hellSecondary(){
        int newDateTable = qrRecordsService.createNewDateTable();
        return newDateTable+"";
    }

    @RequestMapping("/Primary")
    public HashMap<Object, Object> helloPrimary(@RequestParam("id") Long place_id){
        return placeForNormalService.getPlaceById(place_id);
    }

    @RequestMapping("/doInsert")
    public Result doInsert(){
        int i = areaStoreService.AreaConvertToStore(area);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("成功插入次数",i);
        return Result.getInstance(200,"调用成功",i);
    }


    @RequestMapping("/doInsert123")
    public JsonResult doInsert1(){
        int res = 0;
        for (int i = 0; i < authTree.length; i++) {
            res += authorityStoreTableService.convertToStore(authTree[i], true);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("更改记录结果条数",res);
        return JsonResult.getInstance(200,"插入成功",map);
    }

    @RequestMapping("/testGetParam")
    public JsonResult<HashMap<Object,Object>> testGetParam(@RequestBody HashMap hashMap){
        String[] s = new String[]{"longitude","latitude","cropName","Effective_N"};
        Class[] clz = new Class[]{Double.class,Double.class,String.class,Double.class};
        try {
            Object[] objects = ParamGet.fromHashMap(s, clz, hashMap, true);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("param",objects);
            return JsonResult.getInstance(200,"查询成功",map);
        } catch (ParamGet.paramException e) {
            e.printStackTrace();
            throw new ParamIsNullException(e.getMessage());
        }

    }

}
