package com.tonyydl.springbootmall.repository;

import com.tonyydl.springbootmall.data.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Integer> {

    UserPO findByEmail(String email);
}
