package com.hititcs.productmanagementsystem.service;

import com.hititcs.productmanagementsystem.model.Brand;

import java.util.List;

public interface BrandService {
    public List<Brand> findAllBrands();

    public Brand findBrandById(Long id);

    public void createBrand(Brand brand);

    public void updateBrand(Brand brand);

    public void deleteBrand(Long id);
}
