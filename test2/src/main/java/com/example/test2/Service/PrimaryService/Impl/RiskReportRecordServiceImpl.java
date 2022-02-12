package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.RiskReportRecordMapper;
import com.example.test2.POJO.RiskReportRecord;
import com.example.test2.Service.Exception.DeleteException;
import com.example.test2.Service.Exception.InsertException;
import com.example.test2.Service.Exception.UpdateException;
import com.example.test2.Service.PrimaryService.RiskReportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "RiskReportRecordService")
public class RiskReportRecordServiceImpl implements RiskReportRecordService {

    private final RiskReportRecordMapper riskReportRecordMapper;

    @Autowired
    public RiskReportRecordServiceImpl(RiskReportRecordMapper riskReportRecordMapper) {
        this.riskReportRecordMapper = riskReportRecordMapper;
    }

    @Override
    public void addRiskReportRecord(RiskReportRecord riskReportRecord) {
        int rows=riskReportRecordMapper.insertRiskReportRecord(riskReportRecord);
        if(rows!=1){
            throw new InsertException("插入时未知异常");
        }
    }

    @Override
    public void removeRiskReportRecordById(Long id) {
        int rows=riskReportRecordMapper.deleteRiskReportRecordById(id);
        if(rows!=1){
            throw new DeleteException("删除时未知异常");
        }
    }

    @Override
    public void changeRiskReportRecordById(RiskReportRecord riskReportRecord) {
        int rows=riskReportRecordMapper.updateRiskReportRecordById(riskReportRecord);
        if(rows!=1){
            throw new UpdateException("更新时未知异常");
        }
    }

    @Override
    public RiskReportRecord queryRiskReportRecordById(Long id) {
        RiskReportRecord riskReportRecord=riskReportRecordMapper.selectRiskReportRecordById(id);
        return riskReportRecord;
    }
}
