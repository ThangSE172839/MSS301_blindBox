package mss301.mircorservice.msblindbox.config;

import mss301.mircorservice.msblindbox.model.BlindBox;
import mss301.mircorservice.msblindbox.model.BlindBoxCategory;
import mss301.mircorservice.msblindbox.repository.BlindBoxCategoryRepository;
import mss301.mircorservice.msblindbox.repository.BlindBoxRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final BlindBoxRepository boxRepo;
    private final BlindBoxCategoryRepository categoryRepo;

    public DataInitializer(BlindBoxRepository boxRepo, BlindBoxCategoryRepository categoryRepo) {
        this.boxRepo = boxRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("DataInitializer: Checking if boxes exist...");
        if (boxRepo.count() > 0) {
            System.out.println("DataInitializer: Found " + boxRepo.count() + " existing boxes");
            return;
        }


        BlindBoxCategory fantasy = categoryRepo.findByCategoryName("Fantasy")
                .orElseGet(() -> categoryRepo.save(new BlindBoxCategory("Fantasy", "Fantasy & creatures", "Various", "$10-$60")));
        BlindBoxCategory cyber = categoryRepo.findByCategoryName("Cyberpunk")
                .orElseGet(() -> categoryRepo.save(new BlindBoxCategory("Cyberpunk", "Cyberpunk / futuristic", "Various", "10-100")));
        BlindBoxCategory space = categoryRepo.findByCategoryName("Space")
                .orElseGet(() -> categoryRepo.save(new BlindBoxCategory("Space", "Space & explorers", "Various", "10-100")));
        BlindBoxCategory anime = categoryRepo.findByCategoryName("Anime")
                .orElseGet(() -> categoryRepo.save(new BlindBoxCategory("Anime", "Anime themed", "Various", "10-100")));


        List<BlindBox> boxes = new ArrayList<>();
        boxes.add(new BlindBox("Mystic Creatures Series 1", "Rare", new BigDecimal("29.99"), LocalDate.of(2024,1,15), 150, 1, fantasy));
        boxes.add(new BlindBox("Cyberpunk Warriors", "Ultra Rare", new BigDecimal("49.99"), LocalDate.of(2023,11,20), 75, 2, cyber));
        boxes.add(new BlindBox("Fantasy Legends", "Common", new BigDecimal("19.99"), LocalDate.of(2024,2,10), 200, 1, fantasy));
        boxes.add(new BlindBox("Space Explorers", "Epic", new BigDecimal("59.99"), LocalDate.of(2023,12,5), 50, 3, space));
        boxes.add(new BlindBox("Neon Anime Stars", "Legendary", new BigDecimal("99.99"), LocalDate.of(2024,3,1), 25, 1, anime));
        boxes.add(new BlindBox("Retro Arcade Heroes", "Common", new BigDecimal("24.99"), LocalDate.of(2024,1,30), 180, 2, cyber));
        boxes.add(new BlindBox("Mythical Beasts Collection", "Ultra Rare", new BigDecimal("54.99"), LocalDate.of(2023,10,10), 60, 3, fantasy));
        boxes.add(new BlindBox("Superhero Legends", "Rare", new BigDecimal("39.99"), LocalDate.of(2024,2,20), 120, 2, space));
        boxes.add(new BlindBox("Steampunk Adventurers", "Epic", new BigDecimal("69.99"), LocalDate.of(2023,12,15), 40, 3, cyber));
        boxes.add(new BlindBox("Kawaii Anime Pets", "Common", new BigDecimal("14.99"), LocalDate.of(2024,3,5), 250, 1, anime));

        boxRepo.saveAll(boxes);
    }
}
