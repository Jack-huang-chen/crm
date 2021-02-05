package com.study.com.settings.dao;

import com.study.com.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getValue(String code);
}
