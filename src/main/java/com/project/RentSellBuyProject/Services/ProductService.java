package com.project.RentSellBuyProject.Services;

import com.project.RentSellBuyProject.Entity.HomePageDTO;
import com.project.RentSellBuyProject.Entity.ProductDTO;
import com.project.RentSellBuyProject.Entity.ProductEntity;
import com.project.RentSellBuyProject.Repositories.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<HomePageDTO> getProductEntryForHomePage(){
        List<ProductEntity> allProductEntries = productRepository.findAll();
                return allProductEntries.stream().map(productEntity -> {
                    HomePageDTO homePageDTO = new HomePageDTO();
                    if (productEntity.getImageUrls() != null && !productEntity.getImageUrls().isEmpty()){
                        homePageDTO.setImageUrl(productEntity.getImageUrls().get(0));
                    }
                    homePageDTO.setId(productEntity.getId().toHexString());
                    homePageDTO.setProductName(productEntity.getProductName());
                    homePageDTO.setPrice(productEntity.getPrice());
                    homePageDTO.setType(productEntity.getType());
                    return homePageDTO;
                }).collect(Collectors.toList());
    }

    // Just For Testing Exposing the ID
    public Optional<ProductEntity> findProductbyId(ObjectId id){
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        return productEntity;
    }

    public ProductDTO findProuctByDTO(ObjectId id){
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isPresent()){
            ProductEntity productEntity = productEntityOptional.get();

            ProductDTO productDTO = new ProductDTO();
            productDTO.setImageUrls(productEntity.getImageUrls());
            productDTO.setProductName(productEntity.getProductName());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setStartDate(productEntity.getStartDate());
            productDTO.setEndDate(productEntity.getEndDate());
            productDTO.setType(productEntity.getType());

            return productDTO;
        }
        else {
            throw new RuntimeException("Product Not Found try Something Else");
        }
    }
}
