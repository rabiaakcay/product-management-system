package com.hititcs.productmanagementsystem.service;

import com.hititcs.productmanagementsystem.model.Price;

import java.util.List;

public interface PriceService {
    public List<Price> findAllPrices();

    public Price findPriceById(Long id);

    public void createPrice(Price price);

    public void updatePrice(Price price);

    public void deletePrice(Long id);
}
