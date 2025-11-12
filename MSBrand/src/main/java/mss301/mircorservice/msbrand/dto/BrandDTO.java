package mss301.mircorservice.msbrand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private Integer brandID;
    private String brandName;
    private String countryOfOrigin;
}
