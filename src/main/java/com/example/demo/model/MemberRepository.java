package com.example.demo.model;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {
    private final Map<Integer,Member> members = new HashMap<>();
    public void save(Member member)
    {
        members.put(member.getId(),member);
    }
    public void delete(int id)
    {
        members.remove(id);
    }
    public Member find(int id)
    {
        return members.get(id);
    }

    public Map<Integer, Member> findAll() {
        return  this.members;
    }
}
