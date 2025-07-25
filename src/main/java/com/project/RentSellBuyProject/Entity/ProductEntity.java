package com.project.RentSellBuyProject.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "Products")
@Data
public class ProductEntity {

    @Id
    private ObjectId id;

    private String productName;

    private Integer price;

    private Date startDate;

    private Date endDate;

    private String type;

    private List<String> imageUrls;

}
