package com.study.com.workbench.dao;

import com.study.com.settings.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkDao {

    List<ClueRemark> getclueRemarkByClueId(String clueId);
}
