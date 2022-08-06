package com.tradinghub.projectus2.utils;


import com.tradinghub.projectus2.model.account.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentHelper {
    Logger logger = LoggerFactory.getLogger(ContentHelper.class);

    public ContentHelper() {
    }


    private List<Account> productList;

    public List<Account[]> getList(List<Account> list, double items_in_a_row) {
        double length = list.size();
        double capacity = Math.round(length / items_in_a_row);
        List<Account[]> newList = new ArrayList((int) capacity);
        int counter = 0;
        for (int i = 0; i < capacity+1; i++) {
            Account[] pr_array = new Account[4];
            if(checkIfPossible((int) length,counter)){
                pr_array[0] = list.get(counter++);
            }
            if(checkIfPossible((int) length,counter)){
                pr_array[1]=list.get(counter++);
            }
            if(checkIfPossible((int) length,counter)){
                pr_array[2]=list.get(counter++);

            }
            newList.add(pr_array);
        }
        return newList;
    }
    public boolean checkIfPossible(int listSize,int counter){
        return counter<listSize;

    }
    public void showList(List<Account[]> list) {
        logger.info("=================");
        for (Account[] product : list) {
            if((product[0] != null)) {
                logger.info(product[0].getAccountInfo().getHeader());
            }
            if (product[1] != null) {
                logger.info(product[1].getAccountInfo().getHeader());
            }
            if (product[2] != null) {
                logger.info(product[2].getAccountInfo().getHeader());
            }

        }
        logger.info("=================");
    }
}
