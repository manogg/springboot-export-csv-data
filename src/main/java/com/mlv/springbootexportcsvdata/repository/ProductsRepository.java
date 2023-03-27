package com.mlv.springbootexportcsvdata.repository;

import com.mlv.springbootexportcsvdata.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {

    List<Products> findByProductName(String productName);

}
