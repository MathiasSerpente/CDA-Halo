package com.halo.web_app.service;

import com.halo.web_app.client.ProfessionClient;
import com.halo.web_app.model.Profession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {
    private final ProfessionClient professionClient;

    public ProfessionService(ProfessionClient professionClient) {
        this.professionClient = professionClient;
    }

    public List<Profession> findAll(){
        return professionClient.all();
    }

    public Profession byId(Long id){
        return professionClient.byId(id);
    }

    public Profession create(Profession p){
        return professionClient.create(p);
    }

    public Profession update(Long id, Profession p){
        return professionClient.update(id, p);
    }

    public void delete(Long id){
        professionClient.delete(id);
    }
}
