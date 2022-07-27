package com.tradinghub.projectus2.service;

import com.tradinghub.projectus2.model.instagram.InstItem;
import com.tradinghub.projectus2.repository.InstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstService {
    @Autowired
    InstRepository repository;
    public void saveInstAccount(InstItem account){
        repository.save(account);
    }
}
