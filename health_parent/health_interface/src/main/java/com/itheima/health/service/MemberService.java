package com.itheima.health.service;



import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {

    Member findMemberByTelephone(String telephone);

    void add(Member member);

    List<Integer> findMemberCountByMonth(List<String> months);

    Integer findMemberCount();

    Integer findMemberCountAfterDate(String monday);
}
