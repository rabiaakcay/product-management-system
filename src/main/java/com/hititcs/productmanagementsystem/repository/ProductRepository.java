package com.hititcs.productmanagementsystem.repository;

import java.util.List;

import com.hititcs.productmanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%" + " OR p.serialName LIKE %?1%")
    public List<Product> search(String keyword);
}
