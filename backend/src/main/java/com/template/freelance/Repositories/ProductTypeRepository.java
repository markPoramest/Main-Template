package com.template.freelance.Repositories;

import com.template.freelance.Models.Product;
import com.template.freelance.Models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
    @Query("select a from ProductType a where a.productTypeId= :productId and a.status=1")
    ProductType getProductTypeById(@Param("productId")Integer productId);

    @Query("select a from ProductType a where a.status=1 order by a.productName")
    List<ProductType> getProductTypeActive();
}
