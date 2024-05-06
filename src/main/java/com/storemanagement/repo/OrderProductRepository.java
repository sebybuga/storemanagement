package com.storemanagement.repo;

import com.storemanagement.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {

    @Transactional
    void deleteByOrderId(Long orderId);

}