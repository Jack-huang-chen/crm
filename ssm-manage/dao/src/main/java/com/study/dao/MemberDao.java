package com.study.dao;

import com.study.domain.Member;

public interface MemberDao {
    Member findById(String memberId);
}
