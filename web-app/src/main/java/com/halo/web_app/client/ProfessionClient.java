package com.halo.web_app.client;

import com.halo.web_app.model.Profession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-users-requests", contextId = "professionClient", path = "/api/admin/professions")
public interface ProfessionClient {

    @GetMapping("/all")
    List<Profession> all();

    @GetMapping("/{id}")
    Profession byId(@PathVariable Long id);

    @PostMapping
    Profession create(@RequestBody Profession p);

    @PutMapping("/{id}")
    Profession update(@PathVariable Long id, @RequestBody Profession p);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}