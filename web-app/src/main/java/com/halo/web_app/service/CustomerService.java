package com.halo.web_app.service;

import com.halo.web_app.client.CustomerClient;
import com.halo.web_app.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerClient customerClient;

    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public List<Customer> findAll(){
        return customerClient.all();
    }

    public Customer findById(Long id){
        return customerClient.byId(id);
    }

    public Customer create(Customer c){
        return customerClient.create(c);
    }

    public Customer update(Long id, Customer c){
        return customerClient.update(id, c);
    }

    public void delete(Long id){
        customerClient.delete(id);
    }
}
