package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    private int nextId = 1;

    public Member join(Member member) {
        member.setId(nextId++);
        memberRepository.save(member);
        return get(nextId - 1);
    }

    public void withdraw(int id) {
        memberRepository.delete(id);
    }

    public Member get(int id) {
        return memberRepository.find(id);
    }

    public String getNameById(int id) {
        return memberRepository.find(id).getName();
    }

    public ArrayList<Member> getAll() {
        return new ArrayList<>(memberRepository.findAll().values());
    }

}
