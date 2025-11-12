package mss301.mircorservice.msblindbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "blind_boxes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlindBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blind_box_id")
    private Integer blindBoxID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rarity", nullable = false)
    private String rarity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "release_date", nullable = false)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate releaseDate;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    // Chỉ lưu brandID, không tạo quan hệ JPA với Brand service
    @Column(name = "brand_id")
    private Integer brandID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private BlindBoxCategory category;


    public BlindBox(String name, String rarity, BigDecimal price, LocalDate releaseDate, Integer stock, Integer brandID, BlindBoxCategory category) {
        this.name = name;
        this.rarity = rarity;
        this.price = price;
        this.releaseDate = releaseDate;
        this.stock = stock;
        this.brandID = brandID;
        this.category = category;
    }
}
