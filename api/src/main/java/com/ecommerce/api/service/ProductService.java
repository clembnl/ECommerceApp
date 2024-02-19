package com.ecommerce.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerce.api.dto.product.ProductDto;
import com.ecommerce.api.exception.ProductNotExistException;
import com.ecommerce.api.model.Category;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        return product;
    }

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product originalProduct = getProductById(productID);
        if (productDto.getName() == null || productDto.getName().length() == 0) {
            productDto.setName(originalProduct.getName());
        }
        if (productDto.getImageURL() == null || productDto.getImageURL().length() == 0) {
            productDto.setImageURL(originalProduct.getImageURL());
        }
        if (productDto.getDescription() == null || productDto.getDescription().length() == 0) {
            productDto.setDescription(originalProduct.getDescription());
        }
        if (productDto.getPrice() == 0) {
            productDto.setPrice(originalProduct.getPrice());
        }
        if (productDto.getBrand() == null || productDto.getBrand().length() == 0 ) {
            productDto.setBrand(originalProduct.getBrand());
        }
        Product product = getProductFromDto(productDto, category);
        product.setId(productID);
        productRepository.save(product);
    }


    public Product getProductById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }
}
