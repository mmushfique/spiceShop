package com.mush.spiceShop.controller;

import com.mush.spiceShop.domain.Product;
import com.mush.spiceShop.dto.ProductOutputDTO;
import com.mush.spiceShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PostMapping("/upload/{productId}")
    public void uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable Long productId) throws IOException {
        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        Path uploadPath = Paths.get("./productImg/");
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        InputStream inputStream = imageFile.getInputStream();
        try{
            Path filePath = uploadPath.resolve(productId+"_"+fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception e) {
            throw new IOException("Could not save the file" + fileName);
        }

        Product existingProduct= productService.getProductById(productId);
        existingProduct.setImageUrl("./productImg/"+productId+"_"+fileName);
        productService.save(existingProduct);
    }
    @GetMapping("/{id}")
    public ProductOutputDTO getProductById(@PathVariable("id") Long productId){
        return new ProductOutputDTO(productService.getProductById(productId));
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}