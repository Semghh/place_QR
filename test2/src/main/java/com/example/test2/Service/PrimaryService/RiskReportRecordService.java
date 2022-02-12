package com.example.test2.Service.PrimaryService;

import com.example.test2.POJO.RiskReportRecord;

public interface RiskReportRecordService {

    void addRiskReportRecord(RiskReportRecord riskReportRecord);

    void removeRiskReportRecordById(Long id);

    void changeRiskReportRecordById(RiskReportRecord riskReportRecord);

    RiskReportRecord queryRiskReportRecordById(Long id);

}
