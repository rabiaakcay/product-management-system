package com.hititcs.productmanagementsystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please provide a name")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull(message = "Please provide a serial name")
    @Column(name = "serialName", length = 50, nullable = false)
    private String serialName;

    @NotNull(message = "Please provide a description")
    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "products_brands", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
            @JoinColumn(name = "brand_id") })
    private Set<Brand> brands = new HashSet<Brand>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "products_categories", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
            @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "products_prices", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
            @JoinColumn(name = "price_id") })
    private Set<Price> prices = new HashSet<Price>();

    public Product(String name, String serialName, String description) {
        this.name = name;
        this.serialName = serialName;
        this.description = description;
    }

    public void addBrands(Brand brand) {
        this.brands.add(brand);
        brand.getProducts().add(this);
    }

    public void removeBrands(Brand brand) {
        this.brands.remove(brand);
        brand.getProducts().remove(this);
    }

    public void addCategories(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategories(Category category) {
        this.categories.remove(category);
        category.getProducts().remove(this);
    }

    public void addPrices(Price price) {
        this.prices.add(price);
        price.getProducts().add(this);
    }

    public void removePrices(Price price) {
        this.prices.remove(price);
        price.getProducts().remove(this);
    }
}
