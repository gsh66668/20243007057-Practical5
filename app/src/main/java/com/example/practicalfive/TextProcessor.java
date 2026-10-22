package com.example.practicalfive;

public class TextProcessor {

    public static final String MUNICIPALITY_BEIJING = "Beijing";
    public static final String MUNICIPALITY_SHANGHAI = "Shanghai";
    public static final String MUNICIPALITY_TIANJIN = "Tianjin";
    public static final String MUNICIPALITY_CHONGQING = "Chongqing";

    public String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        String normalized = municipality.trim().toLowerCase();

        switch (normalized) {
            case "beijing":
                return MUNICIPALITY_BEIJING;
            case "shanghai":
                return MUNICIPALITY_SHANGHAI;
            case "tianjin":
                return MUNICIPALITY_TIANJIN;
            case "chongqing":
                return MUNICIPALITY_CHONGQING;
            default:
                return null;
        }
    }
}
