package com.project.RentSellBuyProject.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

@Data
public class HomePageDTO {
    private String id;
    private String imageUrl;
    private String productName;
    private Integer price;
    private String type;
}
