package com.tradinghub.projectus2.utils;

import org.springframework.data.domain.Sort;

import java.util.Optional;

public class HomeSortHelper {

    private String price;
    private String followersCount;
    private Sort sortByGroup;
    public HomeSortHelper() {
    }
    public Optional<Sort> getSortParams(){
        if (price == null & followersCount == null) {
            return Optional.empty();
        }
        Sort priceSort = null;
        if (price != null) {
            if (price.equals("ASC")) {
                priceSort = Sort.by("price").ascending();
            } else if (price.equals("DSC")) {
                priceSort = Sort.by("price").descending();
            }
        }
        Sort followersSort = null;
        if (followersCount != null) {
            if (followersCount.equals("ASC")) {
                followersSort = Sort.by("followers").ascending();
            } else if (followersCount.equals("DSC")) {
                followersSort = Sort.by("followers").descending();
            }
        }
        if (priceSort != null) {
            if (followersSort != null) {
                sortByGroup = priceSort.and(followersSort);
                return Optional.of(sortByGroup);
            } else {
                return Optional.of(priceSort);
            }
        } else {
            if(followersSort!=null) {
                sortByGroup = followersSort;
                return Optional.of(sortByGroup);
            }
            return Optional.empty();
        }
    }
    public static Builder newBuilder(){
        return new HomeSortHelper().new Builder();
    }
    public class Builder{
        public Builder() {
        }

        public Builder setPrice(String price){
            HomeSortHelper.this.price=price;
            return this;
        }
        public Builder setFollowers(String followers){
            HomeSortHelper.this.followersCount=followers;
            return this;
        }
        public HomeSortHelper build(){
            return HomeSortHelper.this;
        }
    }

}
