package com.ze.codechallenge.partner.repository;

import com.ze.codechallenge.partner.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {
    String SEARCH_SQL =  "SELECT *  FROM partners ORDER BY ST_Distance(partners.address, ST_MakePoint(:lat, :lng)) ASC";

    @Query(value = SEARCH_SQL, nativeQuery = true)
    List<Partner> searchNearts(@Param("lat") Double lat, @Param("lng") Double lng);

    Boolean existsByDocument(String document);
}
