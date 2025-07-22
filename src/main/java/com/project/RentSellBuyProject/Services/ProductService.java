package com.project.RentSellBuyProject.Services;

import com.project.RentSellBuyProject.Entity.ProductEntity;
import com.project.RentSellBuyProject.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
   @Autowired
   ProductRepository productRepository;
   @Autowired
   CloudinaryImageService cloudinaryImageService;

   public ProductEntity saveProductWithImages(ProductEntity productEntity, MultipartFile[] multipartFiles) throws IOException {
       List<String> imagesUrls = cloudinaryImageService.uploadImages(multipartFiles);
       productEntity.setImageUrls(imagesUrls);
       productRepository.save(productEntity);

       return productEntity;
   }
    public void saveProduct(ProductEntity productEntity){
        productRepository.save(productEntity);
    }
}
