package com.halo.ms_users_requests.model;

import com.halo.ms_users_requests.enums.Convention;
import com.halo.ms_users_requests.enums.ProfessionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Profession {

    private Long id;
    private Convention convention;
    @NotBlank
    @Size(max = 200)
    private String label;
    private boolean executive;
    private ProfessionType type;
    @NotBlank
    @Size(max = 20)
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isExecutive() {
        return executive;
    }

    public void setExecutive(boolean executive) {
        this.executive = executive;
    }

    public ProfessionType getType() {
        return type;
    }

    public void setType(ProfessionType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
