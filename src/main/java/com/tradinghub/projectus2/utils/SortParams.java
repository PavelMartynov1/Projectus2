package com.tradinghub.projectus2.utils;

import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.model.enums.MediaType;


public class SortParams {
    private Integer pageNo;
    private Integer pageSize;
    private String priceOrder;
    private String followersOrder;
    private Integer priceFrom;
    private Integer priceUp;
    private Integer followersFrom;
    private Integer followersUp;
    private String category;
    private String mediaType;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public MediaType getMediaType() {
        if(mediaType==null){
            return null;
        }
        return MediaType.valueOf(mediaType);
    }

    public Integer getPageNo() {
        return pageNo;
    }


    public Integer getPageSize() {
        return pageSize;
    }



    public String getPriceOrder() {
        return priceOrder;
    }



    public String getFollowersOrder() {
        return followersOrder;
    }



    public Integer getPriceFrom() {
        return priceFrom;
    }



    public Integer getPriceUp() {
        return priceUp;
    }



    public Integer getFollowersFrom() {
        return followersFrom;
    }



    public Integer getFollowersUp() {
        return followersUp;
    }



    public AccountCategory getCategory() {
        if(category==null){
            return null;
        }
        return AccountCategory.valueOf(category);
    }


    public static Builder newBuilder(){
        return new SortParams().new Builder();
    }
    public class Builder {
        private Builder(){
        }
        public Builder setPriceOrder(String priceOrder){
            SortParams.this.priceOrder=priceOrder;
            return this;
        }
        public Builder setFollowersOrder(String followersOrder){
            SortParams.this.followersOrder=followersOrder;
            return this;
        }
        public Builder setPriceFrom(Integer priceFrom){
            if(priceFrom==null){
                SortParams.this.priceFrom=0;
                return this;
            }
            SortParams.this.priceFrom=priceFrom;
            return this;
        }
        public Builder setFollowersFrom(Integer followersFrom){
            if(followersFrom==null){
                SortParams.this.followersFrom=0;
                return this;
            }
            SortParams.this.followersFrom=followersFrom;
            return this;
        }
        public Builder setPriceUp(Integer priceUp){
            if(priceUp==null){
                SortParams.this.priceUp=999999999;
                return this;
            }
            SortParams.this.priceUp=priceUp;
            return this;
        }
        public Builder setFollowersUp(Integer followersUp){
            if(followersUp==null){
                SortParams.this.followersUp=999999999;
                return this;
            }
            SortParams.this.followersUp=followersUp;
            return this;
        }
        public Builder setCategory(String category){
            if(category==null || category.equals("all")){
                SortParams.this.category=null;
                return this;
            }
            SortParams.this.category=category;
            return this;
        }
        public Builder setMediaType(String mediaType){
            if(mediaType==null || mediaType.equals("all")){
                SortParams.this.mediaType=null;
                return this;
            }
            SortParams.this.mediaType=mediaType;
            return this;
        }
        public Builder setPageNo(Integer pageNo){
            SortParams.this.pageNo=pageNo;
            return this;
        }
        public Builder setPageSize(Integer pageSize){
            SortParams.this.pageSize=pageSize;
            return this;
        }
        public SortParams build() {
            return SortParams.this;
        }

    }

    @Override
    public String toString() {
        return "SortParams{" +
                "pageNo=" + pageNo +
                ",\n pageSize=" + pageSize +
                ", \npriceOrder='" + priceOrder + '\'' +
                ", \nfollowersOrder='" + followersOrder + '\'' +
                ",\n priceFrom=" + priceFrom +
                ",\n priceUp=" + priceUp +
                ",\n followersFrom=" + followersFrom +
                ",\n followersUp=" + followersUp +
                ",\n category='" + category + '\'' +
                ", \nmediaType='" + mediaType + '\'' +
                '}';
    }
}
