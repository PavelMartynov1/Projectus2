package com.tradinghub.projectus2.model.intefaces;

import com.tradinghub.projectus2.model.enums.Currency;

public interface AccountInfo {
    public void setHeader(String header);
    public String getHeader();
    public void setText(String text);
    public String getText();
    public void setCurrency(Currency currency);
    public Currency getCurrency();
}
