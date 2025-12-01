package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.IFileService;

@RestController
@RequestMapping("/api/v1/evidences")
public class EvidencesController {

    private static Logger logger = LoggerFactory.getLogger(EvidencesController.class);
    private final IFileService fileService;

    public EvidencesController(IFileService fileService) {
        this.fileService = fileService;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(
        @RequestPart(value = "evidence")
        MultipartFile file) throws IOException {
        
        if (file.isEmpty()) 
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("erro", "Arquivo evidence nao encontrado"));

        var name = file.getOriginalFilename();
        var size = file.getSize();
        var contentType = file.getContentType();

        logger.info(">>>> Upload file {} size {} type {}", name, size, contentType);

        fileService.upload(name, contentType, file.getBytes());

        return ResponseEntity.ok(Map.of(
            "name", name,
            "size", size,
            "contentType", contentType
        ));
    }
}
