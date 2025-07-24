package com.project.RentSellBuyProject.Entity;

import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDTO {

    private List<String> imageUrls;

    private String productName;

    private Integer price;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String type;
}
