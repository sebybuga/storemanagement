package com.storemanagement.repo;

import com.storemanagement.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

}