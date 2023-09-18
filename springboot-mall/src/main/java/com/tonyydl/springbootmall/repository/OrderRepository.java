package com.tonyydl.springbootmall.repository;

import com.tonyydl.springbootmall.data.po.OrderPO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderPO, Integer> {

    Integer countByUserId(Integer userId);

    List<OrderPO> findByUserIdOrderByCreatedDateDesc(Integer userId, Pageable pageable);
}
