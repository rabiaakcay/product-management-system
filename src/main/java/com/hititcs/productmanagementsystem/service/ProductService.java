package com.hititcs.productmanagementsystem.service;

import com.hititcs.productmanagementsystem.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProducts();

    public List<Product> searchProducts(String keyword);

    public Product findProductById(Long id);

    public void createProduct(Product product);

    public void updateProduct(Product product);

    public void deleteProduct(Long id);
}
