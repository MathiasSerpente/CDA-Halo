package com.halo.ms_data_gateway.controller;

import com.halo.ms_data_gateway.entity.Customer;
import com.halo.ms_data_gateway.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/all")
    public List<Customer> all() {
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping("/{id}")
    public Customer byId(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client introuvable: " + id));
    }

    @GetMapping("/by-code/{code}")
    public Customer byCode(@PathVariable String code) {
        return customerRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Code client inconnu: " + code));
    }

    @GetMapping("/exists")
    public boolean exists(@RequestParam String code) {
        return customerRepository.existsByCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody Customer body) {
        body.setId(null);
        return customerRepository.save(body);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @Valid @RequestBody Customer body) {
        if (!customerRepository.existsById(id)) throw new NoSuchElementException("Client introuvable: " + id);
        body.setId(id);
        return customerRepository.save(body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        var existing = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client introuvable: " + id));
        customerRepository.delete(existing);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String notFound(NoSuchElementException e) { return e.getMessage(); }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String conflict(DataIntegrityViolationException e) {
        return "Conflit sur les données : " + e.getMostSpecificCause().getMessage();
    }
}
