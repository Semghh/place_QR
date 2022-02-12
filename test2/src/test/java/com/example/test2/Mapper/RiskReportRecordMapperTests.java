package com.example.test2.Mapper;

import com.example.test2.Mapper.Primary.RiskReportRecordMapper;
import com.example.test2.POJO.RiskReportRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RiskReportRecordMapperTests {

    @Autowired
    private RiskReportRecordMapper riskReportRecordMapper;

    @Test
    public void insertRiskReportRecord(){
        RiskReportRecord riskReportRecord=new RiskReportRecord();
        riskReportRecord.setReport_time(System.currentTimeMillis());
        riskReportRecord.setReport_state(1);
        riskReportRecord.setReport_person_id(1L);
        riskReportRecord.setResult("ahhhh");
        riskReportRecord.setApply_type("cart");
        riskReportRecord.setPlace_id(1L);
        riskReportRecord.setType(1);
        System.out.println(riskReportRecordMapper.insertRiskReportRecord(riskReportRecord));
    }

    @Test
    public void deleteRiskReportRecordById(){
        System.out.println(riskReportRecordMapper.deleteRiskReportRecordById(2L));
    }

    @Test
    public void updateRiskReportRecordById(){
        RiskReportRecord riskReportRecord=new RiskReportRecord();
        riskReportRecord.setReport_time(System.currentTimeMillis());
        riskReportRecord.setReport_state(1);
        riskReportRecord.setReport_person_id(1L);
        riskReportRecord.setResult("ahhhh");
        riskReportRecord.setApply_type("normal");
        riskReportRecord.setPlace_id(1L);
        riskReportRecord.setType(1);
        riskReportRecord.setId(3L);
        System.out.println(riskReportRecordMapper.updateRiskReportRecordById(riskReportRecord));
    }

    @Test
    public void selectRiskReportRecordById(){
        System.out.println(riskReportRecordMapper.selectRiskReportRecordById(3L));
    }
}
