package com.tradinghub.projectus2.utils;

import com.tradinghub.projectus2.utils.sort.homeSort.HomeSortHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HomeSortHelperTest {
    HomeSortHelper helper;
    @BeforeEach
    void sortHelper(){
        helper=new HomeSortHelper();
    }
    @DisplayName("All null params test successful")
    @Test
    void shouldReturnOptional_emptyIfNullPriceAndFollowers() {
        Optional<Sort> sort=helper.getSort(null,null);
        assertFalse(sort.isPresent());
    }
    @DisplayName("All empty params test successful")
    @Test
    void shouldReturnOptional_emptyIfEmptyPriceAndFollowers() {
        String price="";
        String followers="";
        Optional<Sort> sort=helper.getSort(price,followers);
        assertFalse(sort.isPresent());
    }
    @DisplayName("Price is null and followers ASC")
    @Test
    void shouldReturnOnlyFollowersSort() {
        String followers="ASC";
        Sort trueSortParams=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.followers"));
        Optional<Sort> sort=helper.getSort(null,followers);
        assertEquals(trueSortParams,sort.get());
    }
    @DisplayName("Price is null and followers DSC")
    @Test
    void shouldReturnOnlyFollowersSort2() {
        String followers="DSC";
        Sort trueSortParams=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.followers"));
        Optional<Sort> sort=helper.getSort(null,followers);
        assertEquals(trueSortParams,sort.get());
    }
    @DisplayName("Price is empty and followers ASC")
    @Test
    void shouldReturnOnlyFollowersSort3() {
        String followers="ASC";
        Sort trueSortParams=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.followers"));
        Optional<Sort> sort=helper.getSort(null,followers);
        assertEquals(trueSortParams,sort.get());
    }
    @DisplayName("Followers is null and price ASC")
    @Test
    void shouldReturnOnlyFollowersSort4() {
        String price="ASC";
        Sort trueSortParams=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.price"));
        Optional<Sort> sort=helper.getSort(price,null);
        assertEquals(trueSortParams,sort.get());
    }
    @DisplayName("Followers is empty and price ASC")
    @Test
    void shouldReturnOnlyFollowersSort5() {
        String price="ASC";
        Sort trueSortParams=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.price"));
        Optional<Sort> sort=helper.getSort(price,"");
        assertEquals(trueSortParams,sort.get());
    }
    @DisplayName("Followers is empty and price DESC")
    @Test
    void shouldReturnOnlyFollowersSort6() {
        String price="DSC";
        Sort trueSortParams=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.price"));
        Optional<Sort> sort=helper.getSort(price,"");
        assertEquals(trueSortParams,sort.get());
    }
    @DisplayName("Followers is null and price DESC")
    @Test
    void shouldReturnOnlyFollowersSort7() {
        String price="DSC";
        Sort trueSortParams=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.price"));
        Optional<Sort> sort=helper.getSort(price,null);
        assertEquals(trueSortParams,sort.get());
    }
    @DisplayName("Followers and price are ASC")
    @Test
    void shouldReturnOnlyFollowersSort8() {
        Sort priceSort=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.price"));
        Sort followersSort=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.followers"));
        Sort trueSortParams=priceSort.and(followersSort);
        Optional<Sort> sort=helper.getSort("ASC","ASC");
        assertEquals(trueSortParams,sort.get());
    }
    @DisplayName("Followers and price are DSC")
    @Test
    void shouldReturnOnlyFollowersSort9() {
        Sort priceSort=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.price"));
        Sort followersSort=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.followers"));
        Sort trueSortParams=priceSort.and(followersSort);
        Optional<Sort> sort=helper.getSort("DSC","DSC");
        assertEquals(trueSortParams,sort.get());
    }


}