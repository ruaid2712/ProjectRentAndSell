package com.project.RentSellBuyProject.Repositories;

import com.project.RentSellBuyProject.Entity.ProductEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, ObjectId> {
}
