
package com.example.WebDemo.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_order_id", unique = true)
    private CustomerOrder customerOrder;

    @ManyToMany
    @JoinTable(
            name = "billing_product",
            joinColumns = @JoinColumn(name = "billing_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    private Double TotalAmount ;

    private Integer Quantity ;

    private Integer Sum ;

    private String Address ;

    private Date date ;


    public Billing(Long id, CustomerOrder customerOrder, Set<Product> products, Double totalAmount, Integer quantity, Integer sum, String address, Date date) {
        this.id = id;
        this.customerOrder = customerOrder;
        this.products = products;
        TotalAmount = totalAmount;
        Quantity = quantity;
        Sum = sum;
        Address = address;
        this.date = date;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Billing() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }


    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getSum() {
        return Sum;
    }

    public void setSum(Integer sum) {
        Sum = sum;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
