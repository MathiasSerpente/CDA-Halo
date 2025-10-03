package com.halo.ms_users_requests.service;

import com.halo.ms_users_requests.client.ProfessionClient;
import com.halo.ms_users_requests.model.Profession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {

    private final ProfessionClient professionClient;

    public ProfessionService(ProfessionClient professionClient) { this.professionClient = professionClient; }

    public List<Profession> findAll() { return professionClient.all(); }
    public Profession findById(Long id) { return professionClient.byId(id); }

    public Profession createProfession(Profession p) {
        normalize(p);
        if (professionClient.exists(p.getCode())) {
            throw new IllegalArgumentException("Code déjà utilisé : " + p.getCode());
        }
        p.setId(null);
        return professionClient.create(p);
    }

    public Profession updateProfession(Long id, Profession form) {
        normalize(form);
        var existing = professionClient.byId(id);
        if (!equalsIgnoreCase(existing.getCode(), form.getCode()) && professionClient.exists(form.getCode())) {
            throw new IllegalArgumentException("Code déjà utilisé : " + form.getCode());
        }
        form.setId(id);
        return professionClient.update(id, form);
    }

    public void deleteProfession(Long id) {
        professionClient.delete(id);
    }

    private void normalize(Profession p) {
        if (p.getCode()  != null) p.setCode(p.getCode().trim().toUpperCase());
        if (p.getLabel() != null) p.setLabel(p.getLabel().trim());
    }
    private boolean equalsIgnoreCase(String a, String b) {
        return a == null ? b == null : a.equalsIgnoreCase(b);
    }
}
