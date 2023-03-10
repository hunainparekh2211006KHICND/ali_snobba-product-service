package com.ali_snobba.productservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ali_snobba.productservice.Model.Product;
import com.ali_snobba.productservice.Repository.IProductRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductRepository productRepo;

    @PostMapping
    public void saveProduct(@RequestBody Product product){
        productRepo.save(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productRepo.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(product);
    }
    
}
