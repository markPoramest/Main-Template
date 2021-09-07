package com.template.freelance.Repositories;

import com.template.freelance.Models.Brand;
import com.template.freelance.Models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    @Query("select a from Brand a where a.brandName= :brandName and a.status=1")
    Brand getBrandByName(@Param("brandName")String brandName);

    @Query("select a from Brand a where a.brandId= :brandId and a.status=1")
    Brand getBrandById(@Param("brandId")Integer brandId);

    @Query("select a from Brand a where a.status=1 order by a.brandName")
    List<Brand> getBrandActive();
}
