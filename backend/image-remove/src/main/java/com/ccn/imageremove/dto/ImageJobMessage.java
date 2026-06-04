package com.ccn.imageremove.dto;

public record ImageJobMessage(
        String originalKey,
        String originalUrl
) {
}