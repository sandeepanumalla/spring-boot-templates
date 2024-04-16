package org.secured.securedwebsockets.response;

import lombok.Builder;

@Builder
public record AuthRespDto(String token, String username) { }
