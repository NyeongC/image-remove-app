package com.ccn.imageremove.controller;

import com.ccn.imageremove.dto.ImageJobMessage;
import com.ccn.imageremove.dto.ImageUploadResponse;
import com.ccn.imageremove.service.ImageMessageProducer;
import com.ccn.imageremove.service.PythonWorkerService;
import com.ccn.imageremove.service.R2StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final PythonWorkerService pythonWorkerService;

    private final R2StorageService r2StorageService;

    private final ImageMessageProducer imageMessageProducer;

        @PostMapping(value = "/upload-temp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<byte[]> uploadTemp(@RequestParam("image") MultipartFile file) throws Exception {
            log.info("이미지 업로드 API 호출됨. fileName={}, contentType={}, size={}",
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize());

            byte[] result = pythonWorkerService.removeBackground(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(result);
        }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUploadResponse> upload(@RequestParam("image") MultipartFile file) throws IOException {
        ImageUploadResponse response = r2StorageService.uploadOriginal(file);

        imageMessageProducer.send(new ImageJobMessage(
                response.key(),
                response.imageUrl()
        ));

        return ResponseEntity.ok(response);
    }
}
