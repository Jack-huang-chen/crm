package com.study.com.workbench.service;

import com.study.com.settings.domain.Clue;
import com.study.com.settings.domain.Tran;
import com.study.com.workbench.domain.Activity;

import java.util.List;

public interface ClueService {
    boolean save(Clue clue);

    Clue detail(String id);

    List<Activity> activityById(String id);

    boolean deleteById(String id);

    boolean bund(String[] aid, String cid);



    boolean convert(String clueId, Tran tran);
}
