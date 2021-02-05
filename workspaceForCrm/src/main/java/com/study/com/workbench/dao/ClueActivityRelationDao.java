package com.study.com.workbench.dao;


import com.study.com.settings.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationDao {


    int bund(ClueActivityRelation car);

    List<ClueActivityRelation> getAcrByClueId(String clueId);
}
