package com.ccn.imageremove.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageUploadResponse {

    private String key;
    private String imageUrl;
}