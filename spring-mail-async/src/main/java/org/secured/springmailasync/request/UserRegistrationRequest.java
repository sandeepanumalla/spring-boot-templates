package org.secured.springmailasync.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@Setter
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;
}
