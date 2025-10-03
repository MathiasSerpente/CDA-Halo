package com.halo.web_app.client;

import com.halo.web_app.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="ms-users-requests", contextId = "customerClient", path="/api/admin/customers")
public interface CustomerClient {

    @GetMapping("/all")
    List<Customer> all();

    @GetMapping("/{id}")
    Customer byId(@PathVariable Long id);

    @PostMapping
    Customer create(@RequestBody Customer c);

    @PutMapping("/{id}")
    Customer update(@PathVariable Long id, @RequestBody Customer c);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
