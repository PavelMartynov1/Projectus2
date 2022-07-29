package com.tradinghub.projectus2.utils;

import com.tradinghub.projectus2.model.enums.AccountCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class HomeSortHelper {
    Logger logger = LoggerFactory.getLogger(HomeSortHelper.class);
    private String price;
    private String followersCount;
    private Sort sortByGroup;
    public HomeSortHelper() {
    }

    public Optional<Sort> getSortParams() {
        int flag = 0;
        if(price==null){
            flag++;
        }else if(price.isEmpty()){
            flag=1;
        }else{
            flag=2;
        }
        if(followersCount==null){
            flag=3;
        }else if(followersCount.isEmpty()){
            flag=3;
        }else{
            flag=4;
        }
        switch (flag) {
            case 0:
               // logger.info("params are not present");
                return Optional.empty();
            case 1:
              //  logger.info("price not present");
                sortByGroup = getSortByValue(followersCount);
                break;
            case 2:
              //  logger.info("followersCount not present");
                sortByGroup = getSortByValue(price);
                break;
            default:
              //  logger.info("all params are present");
                sortByGroup=getSortByValue(price).and(getSortByValue(followersCount));
        }
        return Optional.of(sortByGroup);
    }

    private Sort getSortByValue(String value) {
        Sort tempSort;
        tempSort = value.equals("ASC") ? Sort.by(value).ascending() : Sort.by(value).descending();
        //logger.info(value + "is set" + value);
        return tempSort;
    }

    public static Builder newBuilder() {
        return new HomeSortHelper().new Builder();
    }

    public class Builder {
        public Builder() {
        }

        public Builder setPrice(String price) {
            HomeSortHelper.this.price = price;
            return this;
        }

        public Builder setFollowers(String followers) {
            HomeSortHelper.this.followersCount = followers;
            return this;
        }

        public HomeSortHelper build() {
            return HomeSortHelper.this;
        }
    }

}
