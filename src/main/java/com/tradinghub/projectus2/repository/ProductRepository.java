package com.tradinghub.projectus2.repository;

import com.tradinghub.projectus2.model.Product;
import com.tradinghub.projectus2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
