package com.tradinghub.projectus2.model.enums;

public enum MediaType {
    business("Business"),
    cars("cars"),
    animals("animals"),
    games("Games"),
    tv_series("tv series"),
    culture("Culture"),
    medicine("Medicine"),
    fashion("Beauty and Fashion"),
    politics("Politics"),
    nature("Nature"),
    news("News"),
    sport("Sport"),
    humor("Humor"),
    another("Another"),
    type("All"),
    music("Music"),
    furniture("Furniture"),
    sales("Sales"),
    films("Films"),
    real_estate("Estate"),
    food("Food"),
    work("Work"),
    finances("Finances"),
    ero("Ero"),
    facts("Facts");


    private final String mediaType;

    MediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }
}
