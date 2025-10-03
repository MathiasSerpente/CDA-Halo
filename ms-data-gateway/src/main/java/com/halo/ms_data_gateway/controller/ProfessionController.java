package com.halo.ms_data_gateway.controller;

import com.halo.ms_data_gateway.entity.Profession;
import com.halo.ms_data_gateway.repository.ProfessionRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/professions")
public class ProfessionController {

    private final ProfessionRepository professionRepository;
    public ProfessionController(ProfessionRepository professionRepository) { this.professionRepository = professionRepository; }

    @GetMapping("/all")
    public List<Profession> all() {
        return professionRepository.findAll(Sort.by(Sort.Direction.ASC, "label"));
    }

    @GetMapping("/{id}")
    public Profession byId(@PathVariable Long id) {
        return professionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Profession introuvable: " + id));
    }

    @GetMapping("/by-code/{code}")
    public Profession byCode(@PathVariable String code) {
        return professionRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Code inconnu: " + code));
    }

    @GetMapping("/exists")
    public boolean exists(@RequestParam String code) {
        return professionRepository.existsByCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profession create(@Valid @RequestBody Profession body) {
        body.setId(null);
        return professionRepository.save(body);
    }

    @PutMapping("/{id}")
    public Profession update(@PathVariable Long id, @Valid @RequestBody Profession body) {
        if (!professionRepository.existsById(id)) throw new NoSuchElementException("Profession introuvable: " + id);
        body.setId(id);                   // l’URL fait foi
        return professionRepository.save(body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        var existing = professionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Profession introuvable: " + id));
        professionRepository.delete(existing);
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
