package com.mlv.springbootexportcsvdata.controller;

import com.mlv.springbootexportcsvdata.entity.Products;
import com.mlv.springbootexportcsvdata.service.ProductsService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/new")
    public Products addProducts(@RequestBody Products prod){

        return productsService.saveProducts(prod);

    }

    @GetMapping("/productsByName/{productName}")
    public void getProductsByName(@PathVariable("productName") String productName,
                                  HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        //Set csv file name

        String fileName="Products-list-by-name.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+fileName+"\"");

        //create csv writer
        StatefulBeanToCsv<Products> writer=new StatefulBeanToCsvBuilder<Products>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all product list to csv file

      writer.write(productsService.productsListByName(productName));

    }

    @GetMapping("/products")
    public void getProductsByName(HttpServletResponse response) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {

        //Set csv file name

        String fileName="Products-list.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+fileName+"\"");

        //create csv writer
        StatefulBeanToCsv<Products> writer=new StatefulBeanToCsvBuilder<Products>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all product list to csv file

        writer.write(productsService.productsList());

    }
}
