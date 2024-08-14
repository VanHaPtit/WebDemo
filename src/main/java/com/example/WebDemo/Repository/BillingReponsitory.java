package com.example.WebDemo.Repository;

import com.example.WebDemo.Model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingReponsitory extends JpaRepository<Billing , Long> {
}
