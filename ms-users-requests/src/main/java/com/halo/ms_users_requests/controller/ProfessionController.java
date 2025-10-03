package com.halo.ms_users_requests.controller;

import com.halo.ms_users_requests.model.Profession;
import com.halo.ms_users_requests.service.ProfessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/professions")
public class ProfessionController {

    private final ProfessionService professionService;

    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping("/all")
    public List<Profession> all() {
        return professionService.findAll();
    }

    @GetMapping("/{id}")
    public Profession byId(@PathVariable Long id) {
        return professionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profession create(@RequestBody Profession p) {
        return professionService.createProfession(p);
    }

    @PutMapping("/{id}")
    public Profession update(@PathVariable Long id, @RequestBody Profession p) {
        return professionService.updateProfession(id, p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        professionService.deleteProfession(id);
    }
}
