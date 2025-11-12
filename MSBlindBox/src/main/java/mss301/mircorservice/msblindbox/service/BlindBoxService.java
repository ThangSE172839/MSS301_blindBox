package mss301.mircorservice.msblindbox.service;

import mss301.mircorservice.msblindbox.dto.BlindBoxDTO;
import mss301.mircorservice.msblindbox.model.BlindBox;
import mss301.mircorservice.msblindbox.repository.BlindBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlindBoxService {

    @Autowired
    private BlindBoxRepository blindBoxRepository;

    public List<BlindBoxDTO> getAllBlindBoxes() {
        List<BlindBox> blindBoxes = blindBoxRepository.findAll();
        return blindBoxes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<BlindBoxDTO> getBlindBoxesByCategory(Integer categoryId) {
        List<BlindBox> blindBoxes = blindBoxRepository.findByCategoryCategoryID(categoryId);
        return blindBoxes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<BlindBoxDTO> getBlindBoxesByRarity(String rarity) {
        List<BlindBox> blindBoxes = blindBoxRepository.findByRarity(rarity);
        return blindBoxes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BlindBoxDTO convertToDTO(BlindBox blindBox) {
        BlindBoxDTO dto = new BlindBoxDTO();
        dto.setBlindBoxID(blindBox.getBlindBoxID());
        dto.setName(blindBox.getName());
        dto.setRarity(blindBox.getRarity());
        dto.setPrice(blindBox.getPrice());
        dto.setReleaseDate(blindBox.getReleaseDate());
        dto.setStock(blindBox.getStock());
        dto.setBrandID(blindBox.getBrandID());

        if (blindBox.getCategory() != null) {
            dto.setCategoryID(blindBox.getCategory().getCategoryID());
            dto.setCategoryName(blindBox.getCategory().getCategoryName());
        }

        return dto;
    }
}
