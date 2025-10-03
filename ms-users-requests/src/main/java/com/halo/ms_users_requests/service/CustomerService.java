package com.halo.ms_users_requests.service;

import com.halo.ms_users_requests.client.CustomerClient;
import com.halo.ms_users_requests.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerClient customerClient;

    public CustomerService(CustomerClient customerClient) { this.customerClient = customerClient; }

    public List<Customer> findAll() { return customerClient.all(); }

    public Customer findById(Long id) { return customerClient.byId(id); }

    public Customer create(Customer c) {
        normalize(c);
        if (customerClient.exists(c.getCode())) {
            throw new IllegalArgumentException("Code client déjà utilisé : " + c.getCode());
        }
        c.setId(null);
        return customerClient.create(c);
    }

    public Customer update(Long id, Customer form) {
        normalize(form);
        var existing = customerClient.byId(id);

        if (!equalsIgnoreCase(existing.getCode(), form.getCode()) && customerClient.exists(form.getCode())) {
            throw new IllegalArgumentException("Code client déjà utilisé : " + form.getCode());
        }

        form.setId(id);
        return customerClient.update(id, form);
    }

    public void delete(Long id) {
        customerClient.delete(id);
    }

    private void normalize(Customer c) {
        if (c.getCode()!=null) c.setCode(c.getCode().trim().toUpperCase());
        if (c.getName()!=null) c.setName(c.getName().trim());
    }
    private boolean equalsIgnoreCase(String a, String b) {
        return a == null ? b == null : a.equalsIgnoreCase(b);
    }
}
