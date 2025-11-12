package mss301.mircorservice.msblindbox.repository;

import mss301.mircorservice.msblindbox.model.BlindBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlindBoxRepository extends JpaRepository<BlindBox, Integer> {

    List<BlindBox> findByCategoryCategoryID(Integer categoryId);

    List<BlindBox> findByRarity(String rarity);


}
