package com.halo.ms_data_gateway.enums;

public enum RequestTime {
    TT_8H("Technicien 8 heures"),
    TT_10H("Technicien 10 heures"),
    TA_12H("Artiste cachet 12 heures"),
    OTHER("Autres");

    private final String label;

    RequestTime(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
