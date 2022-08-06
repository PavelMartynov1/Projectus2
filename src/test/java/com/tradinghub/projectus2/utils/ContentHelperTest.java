package com.tradinghub.projectus2.utils;

import com.tradinghub.projectus2.model.account.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContentHelperTest {
    private ContentHelper contentHelper;

    @BeforeEach
    public void setUp(){
        contentHelper=new ContentHelper();
    }
    @DisplayName("list size is less than counter")
    @Test
    void checkIfPossibleTest() {
        assertFalse(contentHelper.checkIfPossible(3,4));
    }
    @DisplayName("list size is more than counter")
    @Test
    void checkIfPossibleTest2() {
        assertTrue(contentHelper.checkIfPossible(4,1));
    }
    @DisplayName("list size equels counter")
    @Test
    void checkIfPossibleTest3() {
        assertFalse(contentHelper.checkIfPossible(3,3));
    }
    @DisplayName("show return an array in a list")
    @Test
    void shouldReturnList(){
        List<Account[]> list=new ArrayList<>(2);
        Account [] accountArray=new Account[4];
        accountArray[0]=new Account();
        accountArray[1]=new Account();
        accountArray[2]=new Account();
        accountArray[3]=new Account();
        list.add(accountArray);
        List<Account> accounts=new ArrayList<>(4);

        assertEquals(list,contentHelper.getList(accounts,4));

    }


}