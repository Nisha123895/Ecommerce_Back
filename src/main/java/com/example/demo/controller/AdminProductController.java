package com.example.demo.controller;

import java.util.List;

import com.example.demo.Exception.productException;
import com.example.demo.model.Product;
import com.example.demo.request.CreateProductRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest req) throws productException{

        Product createdProduct = productService.createProduct(req);

        return new ResponseEntity<Product>(createdProduct,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws productException {

        System.out.println("dlete product controller .... ");
        String msg=productService.deleteProduct(productId);
        System.out.println("dlete product controller .... msg "+msg);
        ApiResponse res=new ApiResponse(msg,true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity<ApiResponse> deleteAllProducts() {
        System.out.println("Deleting all products...");
        String msg = productService.deleteAllProducts();
        System.out.println("Delete all products message: " + msg);
        ApiResponse res = new ApiResponse(msg, true);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct(){

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Product>> recentlyAddedProduct(){

        List<Product> products = productService.recentlyAddedProduct();

        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }


    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProductHandler(@RequestBody Product req,@PathVariable Long productId) throws productException{

        Product updatedProduct=productService.updateProduct(productId, req);

        return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] reqs) throws productException{

        for(CreateProductRequest product:reqs) {
            productService.createProduct(product);
        }

        ApiResponse res=new ApiResponse("products created successfully",true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
    }

}