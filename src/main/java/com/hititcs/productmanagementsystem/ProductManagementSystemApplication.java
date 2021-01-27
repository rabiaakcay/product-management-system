package com.hititcs.productmanagementsystem;

import com.hititcs.productmanagementsystem.model.Brand;
import com.hititcs.productmanagementsystem.model.Product;
import com.hititcs.productmanagementsystem.service.BrandService;
import com.hititcs.productmanagementsystem.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementSystemApplication.class, args);
	}
}

