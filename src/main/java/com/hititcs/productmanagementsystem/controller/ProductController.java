package com.hititcs.productmanagementsystem.controller;

import com.hititcs.productmanagementsystem.model.Product;
import com.hititcs.productmanagementsystem.service.BrandService;
import com.hititcs.productmanagementsystem.service.CategoryService;
import com.hititcs.productmanagementsystem.service.PriceService;
import com.hititcs.productmanagementsystem.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final BrandService brandService;
    private final PriceService priceService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, BrandService brandService, PriceService priceService, CategoryService categoryService) {
        this.productService = productService;
        this.brandService = brandService;
        this.priceService = priceService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/products")
    public String findAllProducts(Model model) {
        final List<Product> products = productService.findAllProducts();

        model.addAttribute("products", products);
        return "list-products";
    }

    @RequestMapping("/searchProduct")
    public String searchProduct(@Param("keyword") String keyword, Model model) {
        final List<Product> products = productService.searchProducts(keyword);

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "list-products";
    }

    @RequestMapping("/product/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model) {
        final Product product = productService.findProductById(id);

        model.addAttribute("product", product);
        return "list-product";
    }

    @GetMapping("/add")
    public String showCreateForm(Product product, Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("brands", brandService.findAllBrands());
        model.addAttribute("prices", priceService.findAllPrices());
        return "add-product";
    }

    @RequestMapping("/add-product")
    public String createProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-product";
        }

        productService.createProduct(product);
        model.addAttribute("product", productService.findAllProducts());
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Product product = productService.findProductById(id);

        model.addAttribute("product", product);
        return "update-product";
    }

    @RequestMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") Long id, Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }

        productService.updateProduct(product);
        model.addAttribute("product", productService.findAllProducts());
        return "redirect:/products";
    }

    @RequestMapping("/remove-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        productService.deleteProduct(id);

        model.addAttribute("product", productService.findAllProducts());
        return "redirect:/products";
    }
}
