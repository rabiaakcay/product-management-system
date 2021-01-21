package com.hititcs.productmanagementsystem.repository;

import com.hititcs.productmanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
