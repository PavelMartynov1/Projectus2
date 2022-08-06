package com.tradinghub.projectus2.model.enums;

public enum AccountCategory {
    inst("Instagram"),
    tg("Telegram"),
    fb("Facebook"),
    ytb("YouTube"),
    category("Category");
    private final String AccountCategory;
    AccountCategory(String category){
        this.AccountCategory=category;
    }

    public String getCategory() {
        return AccountCategory;
    }
}
