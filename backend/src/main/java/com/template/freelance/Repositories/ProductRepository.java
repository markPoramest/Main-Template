package com.template.freelance.Repositories;

import com.template.freelance.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select a from Product a where a.status=1 order by a.productType.productName")
    List<Product> getAllProductActive();
    @Query("select a from Product a where a.productId=:productId and a.status=1")
    Product getProductActiveById(@Param("productId")Integer productId);

    @Query("select a from Product a where a.productType.productTypeId=:productTypeId and a.status=1")
    List<Product> getProductByProductType(@Param("productTypeId")Integer productTypeId);

    @Query("select a from Product a where a.brand.brandId=:brandId and a.status=1")
    List<Product> getProductByBrandId(@Param("brandId")Integer brandId);

}
