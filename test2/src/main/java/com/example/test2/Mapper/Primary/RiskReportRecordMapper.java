package com.example.test2.Mapper.Primary;

import com.example.test2.POJO.RiskReportRecord;
import org.apache.ibatis.annotations.Param;

public interface RiskReportRecordMapper {

    int insertRiskReportRecord(RiskReportRecord riskReportRecord);

    int deleteRiskReportRecordById(@Param("id") Long id);

    int updateRiskReportRecordById(RiskReportRecord riskReportRecord);

    RiskReportRecord selectRiskReportRecordById(@Param("id") Long id);

}
