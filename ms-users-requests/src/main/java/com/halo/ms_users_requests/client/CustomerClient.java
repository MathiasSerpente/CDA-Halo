package com.halo.ms_users_requests.client;

import com.halo.ms_users_requests.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-data-gateway", contextId = "customerClient", path = "/api/customers")
public interface CustomerClient {

    @GetMapping("/all")
    List<Customer> all();

    @GetMapping("/{id}")
    Customer byId(@PathVariable Long id);

    @GetMapping("/by-code/{code}")
    Customer byCode(@PathVariable String code);

    @GetMapping("/exists")
    boolean exists(@RequestParam String code);

    @PostMapping
    Customer create(@RequestBody Customer body);

    @PutMapping("/{id}")
    Customer update(@PathVariable Long id, @RequestBody Customer body);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
