package mss301.mircorservice.msblindbox.controller;

import mss301.mircorservice.msblindbox.dto.BlindBoxDTO;
import mss301.mircorservice.msblindbox.service.BlindBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blind-boxes")
@CrossOrigin(origins = "*")
public class BlindBoxController {

    @Autowired
    private BlindBoxService blindBoxService;

    // Đề yêu cầu: List all items in BlindBoxes table (kèm CategoryName)
    // No permissions required
    @GetMapping
    public ResponseEntity<List<BlindBoxDTO>> getAllBlindBoxes() {
        List<BlindBoxDTO> blindBoxes = blindBoxService.getAllBlindBoxes();
        return ResponseEntity.ok(blindBoxes);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BlindBoxDTO>> getBlindBoxesByCategory(@PathVariable Integer categoryId) {
        List<BlindBoxDTO> blindBoxes = blindBoxService.getBlindBoxesByCategory(categoryId);
        return ResponseEntity.ok(blindBoxes);
    }

    @GetMapping("/rarity/{rarity}")
    public ResponseEntity<List<BlindBoxDTO>> getBlindBoxesByRarity(@PathVariable String rarity) {
        List<BlindBoxDTO> blindBoxes = blindBoxService.getBlindBoxesByRarity(rarity);
        return ResponseEntity.ok(blindBoxes);
    }
}
