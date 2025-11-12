package mss301.mircorservice.msbrand.config;

import mss301.mircorservice.msbrand.model.Brand;
import mss301.mircorservice.msbrand.repository.BrandRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final BrandRepository brandRepository;

    public DataInitializer(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("DataInitializer: Checking if brands exist...");
        if (brandRepository.count() > 0) {
            System.out.println("DataInitializer: Found " + brandRepository.count() + " existing brands");
            return;
        }

        System.out.println("DataInitializer: Initializing brand data...");

        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1, "POP MART", "China"));
        brands.add(new Brand(2, "Funko", "USA"));
        brands.add(new Brand(3, "Kidrobot", "USA"));

        brandRepository.saveAll(brands);
        System.out.println("DataInitializer: Successfully initialized " + brands.size() + " brands");
    }
}
