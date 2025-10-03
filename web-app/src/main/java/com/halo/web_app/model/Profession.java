package com.halo.web_app.model;

import com.halo.web_app.enums.Convention;
import com.halo.web_app.enums.ProfessionType;

public class Profession {

    private Long id;
    private Convention convention;
    private String label;
    private boolean executive;
    private ProfessionType type;
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
