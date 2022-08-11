package com.tradinghub.projectus2.model.enums;

public enum AccountStatus {
    SOLD("Sold"),
    ON_SALE("On sale");
    private final String status;
    AccountStatus(String status){
        this.status=status;
    }

    public String getCategory() {
        return status;
    }
}
