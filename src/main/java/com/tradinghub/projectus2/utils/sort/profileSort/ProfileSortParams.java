package com.tradinghub.projectus2.utils.sort.profileSort;

import com.tradinghub.projectus2.utils.sort.SortParams;

public class ProfileSortParams implements SortParams {
    private Long userId;
    private Integer pageNo;
    private Integer pageSize;
    private String lookUpsOrder;
    private String accountStatus;

    @Override
    public String toString() {
        return "\nProfileSortParams{" +
                "\nuserId=" + userId +
                "\n, pageNo=" + pageNo +
                "\n, pageSize=" + pageSize +
                "\n, lookUpsOrder='" + lookUpsOrder + '\'' +
                "\n, accountStatus='" + accountStatus + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getLookUpsOrder() {
        return lookUpsOrder;
    }

    public String getAccountStatus() {
        return accountStatus;
    }
    public static Builder newBuilder(){
        return new ProfileSortParams().new Builder();
    }
    public class Builder{
        private Builder(){

        }
        public Builder setPageNo(Integer pageNo){
            ProfileSortParams.this.pageNo=pageNo;
            return this;
        }
        public Builder setSize(Integer pageSize){
            ProfileSortParams.this.pageSize=pageSize;
            return this;
        }
        public Builder setLookUpsOrder(String lookUpsOrder){
            ProfileSortParams.this.lookUpsOrder=lookUpsOrder;
            return this;
        }
        public Builder setAccountStatus(String accountStatus){
            ProfileSortParams.this.accountStatus=accountStatus;
            return this;
        }
        public Builder setUserId(Long userId){
            ProfileSortParams.this.userId=userId;
            return this;
        }
        public ProfileSortParams build(){
            return ProfileSortParams.this;
        }
    }
}
