package com.itheima.health.dao;

import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {

    Member findMemberByTelephone(String telephone);

    void add(Member member);

    Integer findMemberCountByMonth(String month);

    Integer findMemberCount();

    Integer findMemberCountAfterDate(String monday);
}
