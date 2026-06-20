package com.ccn.imageremove.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthRefreshRequest {

    private String refreshToken;
}