package com.example.demo.model.DAO;

import com.example.demo.model.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDAO {
    List<Member> findAll();

    Optional<Member> findById(int id);

    Member insert(Member member);

    void deleteById(int id);
}
