package com.tradinghub.projectus2.model.instagram;

import com.tradinghub.projectus2.model.Item;
import com.tradinghub.projectus2.model.ItemDetails.ItemDetails;
import com.tradinghub.projectus2.model.enums.Currency;
import com.tradinghub.projectus2.model.enums.ItemEnum;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class InstItem extends Item {
    private String price;
    private Currency currency;
    private BigDecimal income;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "itemDetails_id")
    private ItemDetails itemDetails;
    private ItemEnum itemEnum;

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    public ItemEnum getItemEnum() {
        return itemEnum;
    }

    public void setItemEnum(ItemEnum itemEnum) {
        this.itemEnum = itemEnum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
