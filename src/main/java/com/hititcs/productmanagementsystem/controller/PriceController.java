package com.hititcs.productmanagementsystem.controller;

import java.util.List;

import com.hititcs.productmanagementsystem.model.Price;
import com.hititcs.productmanagementsystem.service.PriceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @RequestMapping("/prices")
    public String findAllprices(Model model) {
        final List<Price> prices = priceService.findAllPrices();

        model.addAttribute("prices", prices);
        return "list-prices";
    }

    @RequestMapping("/price/{id}")
    public String findPriceById(@PathVariable("id") Long id, Model model) {
        final Price price = priceService.findPriceById(id);

        model.addAttribute("price", price);
        return "list-price";
    }

    @GetMapping("/addPrice")
    public String showCreateForm(Price price) {
        return "add-price";
    }

    @RequestMapping("/add-price")
    public String createPrice(@Valid Price price, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-price";
        }

        priceService.createPrice(price);
        model.addAttribute("price", priceService.findAllPrices());
        return "redirect:/prices";
    }

    @GetMapping("/updatePrice/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Price price = priceService.findPriceById(id);

        model.addAttribute("price", price);
        return "update-price";
    }

    @RequestMapping("/update-price/{id}")
    public String updatePrice(@PathVariable("id") Long id, Price price, BindingResult result, Model model) {
        if (result.hasErrors()) {
            price.setId(id);
            return "update-prices";
        }

        priceService.updatePrice(price);
        model.addAttribute("price", priceService.findAllPrices());
        return "redirect:/prices";
    }

    @RequestMapping("/remove-price/{id}")
    public String deletePrice(@PathVariable("id") Long id, Model model) {
        priceService.deletePrice(id);

        model.addAttribute("price", priceService.findAllPrices());
        return "redirect:/prices";
    }

}
