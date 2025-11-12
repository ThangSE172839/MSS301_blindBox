package mss301.mircorservice.msblindbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlindBoxDTO {
    private Integer blindBoxID;
    private String name;
    private Integer categoryID;
    private String categoryName;
    private Integer brandID;
    private String brandName;
    private String rarity;
    private BigDecimal price;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate releaseDate;
    private Integer stock;
}
