package com.example.test2.Controllers;

import com.example.test2.Controllers.Exception.ParameIsNullException;
import com.example.test2.POJO.RiskReportRecord;
import com.example.test2.Service.PrimaryService.RiskReportRecordService;
import com.example.test2.Util.JsonResult;
import com.example.test2.Util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/riskReportRecord")
public class RiskReportRecordController extends BaseController{

    private final RiskReportRecordService riskReportRecordService;

    @Autowired
    public RiskReportRecordController(RiskReportRecordService riskReportRecordService) {
        this.riskReportRecordService = riskReportRecordService;
    }

    @PostMapping(value = "/addRiskReportRecord")
    public JsonResult<Void> addRiskReportRecord(RiskReportRecord riskReportRecord){
        riskReportRecord.setId(-1L);
        if(!ParameterUtil.parameterCheck(riskReportRecord)){
            throw new ParameIsNullException("指定参数不能为空");
        }
        riskReportRecordService.addRiskReportRecord(riskReportRecord);
        return new JsonResult<>(OK);
    }

    @PostMapping(value = "/removeRiskReportRecordById")
    public JsonResult<Void> removeRiskReportRecordById(Long id){
        riskReportRecordService.removeRiskReportRecordById(id);
        return new JsonResult<>(OK);
    }

    @PostMapping(value = "/changeRiskReportRecordById")
    public JsonResult<Void> changeRiskReportRecordById(RiskReportRecord riskReportRecord){
        if(!ParameterUtil.parameterCheck(riskReportRecord)){
            throw new ParameIsNullException("指定参数不能为空");
        }
        riskReportRecordService.changeRiskReportRecordById(riskReportRecord);
        return new JsonResult<>(OK);
    }

    @GetMapping(value = "/queryRiskReportRecordById")
    public JsonResult<HashMap<String,RiskReportRecord>> queryRiskReportRecordById(Long id){
        RiskReportRecord riskReportRecord=riskReportRecordService.queryRiskReportRecordById(id);
        HashMap<String,RiskReportRecord> hashMap=new HashMap<>();
        hashMap.put("riskReportRecord",riskReportRecord);
        return new JsonResult<>(OK,hashMap);
    }
}
