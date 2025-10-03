package com.halo.web_app.enums;

public enum RequestStatus {
    DRAFT("Brouillon"),
    SUBMITTED("En cours"),
    PROCESSED("Traité"),
    ARCHIVED("Archivé");

    private final String label;

    RequestStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
