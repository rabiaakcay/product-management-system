package com.hititcs.productmanagementsystem.service.impl;

import com.hititcs.productmanagementsystem.exception.NotFoundException;
import com.hititcs.productmanagementsystem.model.Product;
import com.hititcs.productmanagementsystem.repository.ProductRepository;
import com.hititcs.productmanagementsystem.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
   private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Product> searchProducts(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found with ID %d", id)));    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found with ID %d", id)));

        productRepository.deleteById(product.getId());
    }
}
