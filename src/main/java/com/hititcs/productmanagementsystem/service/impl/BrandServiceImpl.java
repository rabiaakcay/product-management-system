package com.hititcs.productmanagementsystem.service.impl;

import com.hititcs.productmanagementsystem.exception.NotFoundException;
import com.hititcs.productmanagementsystem.model.Brand;
import com.hititcs.productmanagementsystem.repository.BrandRepository;
import com.hititcs.productmanagementsystem.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Brand findBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Brand not found with ID %d", id)));
    }

    @Override
    public void createBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
            brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        final Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Brand not found with ID %d", id)));

        brandRepository.deleteById(brand.getId());
    }
}
