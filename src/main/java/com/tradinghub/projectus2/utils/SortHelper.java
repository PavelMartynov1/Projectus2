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
//        //if params are all null we return empty Optional
//        if(price==null&followersCount==null){
//            logger.info("params are null");
//            return Optional.empty();
//
//        }
//        //if price null set it to ignore
//        if(price==null){
//            price="ignore";
//            logger.info("price is null and set to ignore");
//        }
//        //if followersCount null set it to ignore
//        if(followersCount==null){
//            followersCount="ignore";
//            logger.info("followersCount is null and set to ignore");
//
//        }
//        Sort priceSort = null;
//        if(price.equals("ignore")&followersCount.equals("ignore")){
//            logger.info("params are ignore");
//            return Optional.empty();
//        }
//        if(!price.equals("ignore")){
//            priceSort=Sort.by("price");
//            if(price.equals("ASC")) {
//                priceSort = priceSort.ascending();
//                logger.info("price is set asc");
//            } else{
//                logger.info("price is set desc");
//                priceSort=priceSort.descending();
//            }
//        }
//
//        Sort followersSort = null;
//        if(!followersCount.equals("ignore")){
//            followersSort=Sort.by("followers");
//            if(followersCount.equals("ASC")) {
//                logger.info("followersSort is set asc");
//                followersSort = followersSort.ascending();
//            } else{
//                logger.info("followersSort is set desc");
//                followersSort=followersSort.descending();
//            }
//
//        }
//        if(priceSort!=null){
//            if(followersSort!=null){
//                sortByGroup=priceSort.and(followersSort);
//            }
//        }
//        return Optional.of(sortByGroup);
//    }

        if (price == null & followersCount == null) {
            logger.info("params are null");
            return Optional.empty();
        }

        Sort priceSort = null;
        if (price != null) {
            if (price.equals("ASC")) {
                priceSort=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.price"));
                //priceSort = Sort.by("price").ascending();
                logger.info("price is set asc");
            } else if (price.equals("DSC")) {
                priceSort=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.price"));
                //priceSort = Sort.by("price").descending();
                logger.info("price is set desc");
            }
        }
        Sort followersSort = null;
        if (followersCount != null) {
            if (followersCount.equals("ASC")) {
                followersSort=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.followers"));
                //followersSort = Sort.by("followers").ascending();
                logger.info("followers is set asc");
            } else if (followersCount.equals("DSC")) {
                followersSort=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.followers"));
              //  followersSort = Sort.by("followers").descending();
                logger.info("price is set desc");
            }
        }
        if (priceSort != null) {
            logger.info("price is not null");
            if (followersSort != null) {
                logger.info("followers also not null");
                sortByGroup = priceSort.and(followersSort);
                return Optional.of(sortByGroup);
            } else {
                logger.info("followers are null");
                return Optional.of(priceSort);
            }
        } else {
            if(followersSort!=null) {
                logger.info("price is null");
                sortByGroup = followersSort;
                return Optional.of(sortByGroup);
            }
            logger.info("followers are null");
            return Optional.empty();
        }
    }

}
