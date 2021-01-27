package com.hititcs.productmanagementsystem.controller;

import java.util.List;

import com.hititcs.productmanagementsystem.model.Brand;
import com.hititcs.productmanagementsystem.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping("/brands")
    public String findAllBrands(Model model) {
        final List<Brand> brands = brandService.findAllBrands();

        model.addAttribute("brands", brands);
        return "list-brands";
    }

    @RequestMapping("/brand/{id}")
    public String findBrandById(@PathVariable("id") Long id, Model model) {
        final Brand brand = brandService.findBrandById(id);

        model.addAttribute("brand", brand);
        return "list-brand";
    }

    @GetMapping("/addBrand")
    public String showCreateForm(Brand brand) {
        return "add-brand";
    }

    @RequestMapping("/add-brand")
    public String createBrand(@Valid Brand brand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-brand";
        }

        brandService.createBrand(brand);
        model.addAttribute("brand", brandService.findAllBrands());
        return "redirect:/brands";
    }

    @GetMapping("/updateBrand/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Brand brand = brandService.findBrandById(id);

        model.addAttribute("brand", brand);
        return "update-brand";
    }

    @RequestMapping("/update-brand/{id}")
    public String updatebrand(@PathVariable("id") Long id, Brand brand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            brand.setId(id);
            return "update-brand";
        }

        brandService.updateBrand(brand);
        model.addAttribute("brand", brandService.findAllBrands());
        return "redirect:/brands";
    }

    @RequestMapping("/remove-brand/{id}")
    public String deleteBrand(@PathVariable("id") Long id, Model model) {
        brandService.deleteBrand(id);

        model.addAttribute("brand", brandService.findAllBrands());
        return "redirect:/brands";
    }

}

