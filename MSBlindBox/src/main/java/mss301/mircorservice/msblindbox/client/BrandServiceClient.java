package mss301.mircorservice.msblindbox.client;

import mss301.mircorservice.msblindbox.dto.BrandDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brand-service", url = "${brand.service.url:http://localhost:8082}")
public interface BrandServiceClient {

    @GetMapping("/api/brands/{id}")
    BrandDTO getBrandById(@PathVariable("id") Integer brandId);
}
