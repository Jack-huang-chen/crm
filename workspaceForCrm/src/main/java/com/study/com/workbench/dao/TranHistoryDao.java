package com.study.com.workbench.dao;

import com.study.com.settings.domain.TranHistory;

import java.util.List;

public interface TranHistoryDao {



    int save(TranHistory tranHistory);

    List<TranHistory> showHistory(String tranId);
}
