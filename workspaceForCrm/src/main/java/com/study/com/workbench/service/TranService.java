package com.study.com.workbench.service;

import com.study.com.settings.domain.Tran;
import com.study.com.settings.domain.TranHistory;

import java.util.List;
import java.util.Map;

public interface TranService {
    boolean tranSave(Tran tran, String customerName);

    Tran detail(String id);

    List<TranHistory> showHistory(String tranId);

    boolean changeStage(Tran tran);

    Map<String, Object> getCharts();
}
