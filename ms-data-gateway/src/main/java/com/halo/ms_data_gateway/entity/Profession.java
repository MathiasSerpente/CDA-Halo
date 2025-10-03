package com.halo.ms_data_gateway.entity;

import com.halo.ms_data_gateway.enums.Convention;
import com.halo.ms_data_gateway.enums.ProfessionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "professions")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Convention convention;

    @NotBlank(message = "Le libellé est obligatoire")
    @Size(max = 100, message = "Le libellé ne doit pas dépasser 200 caractères")
    @Column(nullable = false, length = 100)
    private String label;

    @Column(nullable = false)
    private boolean executive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfessionType type;

    @NotBlank(message = "Le code est obligatoire")
    @Size(max = 20, message = "Le code ne doit pas dépasser 20 caractères")
    @Column(nullable = false, unique = true, length = 20)
    private String code;

    public Profession() {
    }

    public Profession(Long id, Convention convention, String label, boolean executive, ProfessionType type, String code) {
        this.id = id;
        this.convention = convention;
        this.label = label;
        this.executive = executive;
        this.type = type;
        this.code = code;
    }

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
