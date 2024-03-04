package com.bookstore.be.repository;
import com.bookstore.be.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    Customer findByFirstName(String firstName);
}
