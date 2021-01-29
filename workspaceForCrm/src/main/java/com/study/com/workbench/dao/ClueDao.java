package com.study.com.workbench.dao;


import com.study.com.settings.domain.Clue;
import com.study.com.workbench.domain.Activity;

import java.util.List;

public interface ClueDao {


    int save(Clue clue);

    Clue detail(String id);

    List<Activity> activityById(String id);

    int deleteById(String id);


    Clue getClue(String clueId);
}
