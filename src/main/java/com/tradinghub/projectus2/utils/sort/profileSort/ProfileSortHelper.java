package com.tradinghub.projectus2.utils.sort.profileSort;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class ProfileSortHelper {
    Logger logger = LoggerFactory.getLogger(ProfileSortHelper.class);
    public Optional<Sort> getSort(String lookUpsOrder){
        if(lookUpsOrder==null ){
            return Optional.empty();
        }
        Sort lookUpsSort;
        if(lookUpsOrder!=null){
            if(lookUpsOrder.equals("ASC")){
                lookUpsSort=Sort.by(new Sort.Order(Sort.Direction.ASC,"accountInfo.lookUps"));
                return Optional.of(lookUpsSort);
            } else if(lookUpsOrder.equals("DSC")){
                lookUpsSort=Sort.by(new Sort.Order(Sort.Direction.DESC,"accountInfo.lookUps"));
                return Optional.of(lookUpsSort);
            }
        }
    return Optional.empty();
    }
}
