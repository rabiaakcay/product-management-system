package com.hititcs.productmanagementsystem.service.impl;

import com.hititcs.productmanagementsystem.exception.NotFoundException;
import com.hititcs.productmanagementsystem.model.Price;
import com.hititcs.productmanagementsystem.repository.PriceRepository;
import com.hititcs.productmanagementsystem.service.PriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Price> findAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price findPriceById(Long id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Price not found  with ID %d", id)));
    }

    @Override
    public void createPrice(Price price) {
        priceRepository.save(price);
    }

    @Override
    public void updatePrice(Price price) {
        priceRepository.save(price);
    }

    @Override
    public void deletePrice(Long id) {
        final Price price = priceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Price not found  with ID %d", id)));

        priceRepository.deleteById(price.getId());
    }
}
