package mss301.mircorservice.msblindbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlindBoxRequest {
    private String name;
    private Integer categoryID;
    private Integer brandID;
    private String rarity;
    private BigDecimal price;
    private Integer stock;
}
