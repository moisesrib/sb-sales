package com.sb.controller;

import com.sb.model.Product;
import com.sb.model.ProductRepository;

public class ProductController {

    private final ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    public Product getProductByBarcode(String barcode) {
        if (barcode == null || barcode.trim().isEmpty()) {
            System.err.println("O código de barras não pode ser vazio ou nulo.");
            return null;
        }
        return productRepository.findByBarcode(barcode);
    }
}
