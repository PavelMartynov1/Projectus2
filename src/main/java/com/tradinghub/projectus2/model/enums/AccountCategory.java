package com.tradinghub.projectus2.model.enums;

public enum AccountCategory {
    inst("Instagram"),
    tg("Telegram"),
    fb("Facebook"),
    ytb("YouTube");
    private final String category;
    AccountCategory(String category){
        this.category=category;
    }

    public String getCategory() {
        return category;
    }
}
