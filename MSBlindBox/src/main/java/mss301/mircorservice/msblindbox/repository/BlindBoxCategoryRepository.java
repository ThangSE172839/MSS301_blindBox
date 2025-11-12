package mss301.mircorservice.msblindbox.repository;

import mss301.mircorservice.msblindbox.model.BlindBoxCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlindBoxCategoryRepository extends JpaRepository<BlindBoxCategory, Integer> {

    Optional<BlindBoxCategory> findByCategoryName(String categoryName);

}
