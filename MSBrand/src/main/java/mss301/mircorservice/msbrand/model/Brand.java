package mss301.mircorservice.msbrand.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer brandID;

    @Column(name = "brand_name", nullable = false, length = 100)
    private String brandName;

    @Column(name = "country_of_origin", length = 100)
    private String countryOfOrigin;
}




