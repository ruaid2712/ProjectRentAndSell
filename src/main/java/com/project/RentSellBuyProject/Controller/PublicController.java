package com.project.RentSellBuyProject.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.RentSellBuyProject.Entity.HomePageDTO;
import com.project.RentSellBuyProject.Entity.ProductDTO;
import com.project.RentSellBuyProject.Entity.ProductEntity;
import com.project.RentSellBuyProject.Services.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    ProductService productService;

    @GetMapping("/health-check")
    public String health(){
        return "OK";
    }
    @PostMapping(value = "/create",consumes = "multipart/form-data")
    public ResponseEntity<?> createProduct(
            @RequestPart("product") String productInStringFormat,
            @RequestPart("image")MultipartFile[] multipartFiles) throws JsonProcessingException {

        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductEntity productEntity = objectMapper.readValue(productInStringFormat,ProductEntity.class);

            productService.saveProductWithImages(productEntity,multipartFiles);
            return new ResponseEntity<>(productEntity,HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/home-page")
    public ResponseEntity<?> homePage(){  // ResponseEntity<List<HomePageDTO>>
        try {
            List<HomePageDTO> productEntryForHomePage = productService.getProductEntryForHomePage();
            return new ResponseEntity<>(productEntryForHomePage, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{myId}")
    public ResponseEntity<?> findIndividualEntityById(@PathVariable ObjectId myId){
        try {
            ProductDTO productEntryUsingDTO = productService.findProuctByDTO(myId);
            return new ResponseEntity<>(productEntryUsingDTO,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
