package mss301.mircorservice.msbrand.repository;

import mss301.mircorservice.msbrand.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    List<Brand> findByBrandNameContaining(String brandName);

    List<Brand> findByCountryOfOrigin(String countryOfOrigin);
}
