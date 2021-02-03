package com.hititcs.productmanagementsystem.repository;

import com.hititcs.productmanagementsystem.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {
}
