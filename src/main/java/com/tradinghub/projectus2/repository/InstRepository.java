package com.tradinghub.projectus2.repository;

import com.tradinghub.projectus2.model.instagram.InstItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstRepository extends JpaRepository<InstItem,Long> {

}
