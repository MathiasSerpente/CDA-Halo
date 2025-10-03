package com.halo.ms_users_requests.client;

import com.halo.ms_users_requests.model.Profession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-data-gateway", contextId = "professionClient", path = "/api/professions")
public interface ProfessionClient {

    @GetMapping("/all")
    List<Profession> all();

    @GetMapping("/{id}")
    Profession byId(@PathVariable Long id);

    @GetMapping("/by-code/{code}")
    Profession byCode(@PathVariable String code);

    @GetMapping("/exists")
    boolean exists(@RequestParam String code);

    @PostMapping Profession create(@RequestBody Profession body);
    @PutMapping("/{id}")
    Profession update(@PathVariable Long id, @RequestBody Profession body);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
