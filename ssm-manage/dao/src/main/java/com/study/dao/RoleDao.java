package com.study.dao;

import com.study.domain.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findById(String id);
}
