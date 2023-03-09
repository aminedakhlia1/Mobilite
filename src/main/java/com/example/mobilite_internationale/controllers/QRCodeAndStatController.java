package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.dto.SpecialityDTO;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class QRCodeAndStatController {

    @Autowired
    MobiliteInterface mobiliteInterface;

    @GetMapping("generateQRCodeForOpportunity/{id-opportunity}")
    public ResponseEntity<byte[]> generateQRCodeForOpportunity(@PathVariable("id-opportunity") Integer idOpportunity) {
        try {
            byte[] qrCode = mobiliteInterface.generateQRCodeForOpportunity(idOpportunity);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stat-get-popular-specialities")
    public List<SpecialityDTO> getSpecialityCounts() {
        return mobiliteInterface.getPopularSpecialties();
    }
}
