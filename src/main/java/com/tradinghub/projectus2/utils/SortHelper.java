package com.tradinghub.projectus2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SortHelper {
    public SortHelper() {
    }

    Logger logger = LoggerFactory.getLogger(SortHelper.class);
    private Sort sortByGroup;

    public Optional<Sort> getSort(String price, String followersCount) {
        if (price == null & followersCount == null) {
            return Optional.empty();
        }

        Sort priceSort = null;
        if (price != null) {
            if (price.equals("ASC")) {
                priceSort=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.price"));
            } else if (price.equals("DSC")) {
                priceSort=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.price"));
            }
        }
        Sort followersSort = null;
        if (followersCount != null) {
            if (followersCount.equals("ASC")) {
                followersSort=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.followers"));

            } else if (followersCount.equals("DSC")) {
                followersSort=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.followers"));

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

}
