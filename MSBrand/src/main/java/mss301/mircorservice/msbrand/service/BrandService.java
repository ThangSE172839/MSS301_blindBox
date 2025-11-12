package mss301.mircorservice.msbrand.service;

import mss301.mircorservice.msbrand.dto.BrandDTO;
import mss301.mircorservice.msbrand.model.Brand;
import mss301.mircorservice.msbrand.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<BrandDTO> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<BrandDTO> getBrandById(Integer id) {
        return brandRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<BrandDTO> getBrandsByCountry(String country) {
        List<Brand> brands = brandRepository.findByCountryOfOrigin(country);
        return brands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BrandDTO createBrand(Brand brand) {
        Brand savedBrand = brandRepository.save(brand);
        return convertToDTO(savedBrand);
    }

    public Optional<BrandDTO> updateBrand(Integer id, Brand brandDetails) {
        return brandRepository.findById(id)
                .map(brand -> {
                    brand.setBrandName(brandDetails.getBrandName());
                    brand.setCountryOfOrigin(brandDetails.getCountryOfOrigin());
                    return convertToDTO(brandRepository.save(brand));
                });
    }

    public boolean deleteBrand(Integer id) {
        return brandRepository.findById(id)
                .map(brand -> {
                    brandRepository.delete(brand);
                    return true;
                }).orElse(false);
    }

    private BrandDTO convertToDTO(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setBrandID(brand.getBrandID());
        dto.setBrandName(brand.getBrandName());
        dto.setCountryOfOrigin(brand.getCountryOfOrigin());
        return dto;
    }
}
