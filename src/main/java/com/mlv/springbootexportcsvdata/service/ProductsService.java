package com.mlv.springbootexportcsvdata.service;

import com.mlv.springbootexportcsvdata.entity.Products;
import com.mlv.springbootexportcsvdata.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public Products saveProducts(Products products){

        return productsRepository.save(products);

    }

    public List<Products> productsListByName(String productName){

        List<Products> listOfProducts=new ArrayList<>();

        listOfProducts= productsRepository.findByProductName(productName);

        return listOfProducts;
    }

    public List<Products> productsList(){

        List<Products> listOfProduct=new ArrayList<>();

        listOfProduct=productsRepository.findAll();

        return listOfProduct;
    }
}
