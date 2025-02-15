package com.shyloostyle.manytomany.product.repository;

import com.shyloostyle.manytomany.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<Product,Long> {
}
