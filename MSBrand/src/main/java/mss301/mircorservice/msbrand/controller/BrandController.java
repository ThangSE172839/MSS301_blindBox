package mss301.mircorservice.msbrand.controller;

import mss301.mircorservice.msbrand.dto.BrandDTO;
import mss301.mircorservice.msbrand.model.Brand;
import mss301.mircorservice.msbrand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin(origins = "*")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/country/{country}")
    public List<BrandDTO> getBrandsByCountry(@PathVariable String country) {
        return brandService.getBrandsByCountry(country);
    }

    @PostMapping
    public BrandDTO createBrand(@RequestBody Brand brand) {
        return brandService.createBrand(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Integer id, @RequestBody Brand brandDetails) {
        return brandService.updateBrand(id, brandDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id) {
        return brandService.deleteBrand(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
