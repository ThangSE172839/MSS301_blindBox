package mss301.mircorservice.msblindbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blind_box_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlindBoxCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "rarity_level", length = 50)
    private String rarityLevel;

    @Column(name = "price_range", length = 100)
    private String priceRange;

    public BlindBoxCategory(String categoryName, String description, String rarityLevel, String priceRange) {
        this.categoryName = categoryName;
        this.description = description;
        this.rarityLevel = rarityLevel;
        this.priceRange = priceRange;
    }
}
