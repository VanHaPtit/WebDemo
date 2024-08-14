
package com.example.WebDemo.Model;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Tên tệp hình ảnh

    private String contentType; // Loại nội dung của hình ảnh

    private long size; // Kích thước của hình ảnh (byte)

    private String url; // Đường dẫn lưu trữ hình ảnh trên hệ thống tập tin

    @ManyToOne
    @JoinColumn(name = "product_id") // Tên cột khóa ngoại
    private Product product;

    // Constructors, getters, and setters

    public Image() {
    }

    public Image(String name, String contentType, long size, String url, Product product) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.url = url;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

