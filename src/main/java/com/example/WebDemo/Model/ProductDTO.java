package com.example.WebDemo.Model;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class ProductDTO {
    private Long id ;
    private String productName;
    private Double price;
    private String description;
    private Category category;
    private List<MultipartFile> images;

    // Getters và Setters

    public String getProductName() {
        return productName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
