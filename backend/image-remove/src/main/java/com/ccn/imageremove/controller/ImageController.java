package com.ccn.imageremove.controller;

import com.ccn.imageremove.service.PythonWorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final PythonWorkerService pythonWorkerService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> upload(@RequestParam("image") MultipartFile file) throws Exception {
        log.info("이미지 업로드 API 호출됨. fileName={}, contentType={}, size={}",
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize());

        byte[] result = pythonWorkerService.removeBackground(file);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(result);
    }
}
