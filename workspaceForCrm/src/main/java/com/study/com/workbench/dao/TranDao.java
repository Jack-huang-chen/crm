package com.study.com.workbench.dao;

import com.study.com.settings.domain.Tran;

import java.util.List;
import java.util.Map;

public interface TranDao {



    int save(Tran tran);

    Tran detail(String id);

    int changeStage(Tran tran);

    int getTotal();

    List<Map<String, Object>> getCharts();
}
