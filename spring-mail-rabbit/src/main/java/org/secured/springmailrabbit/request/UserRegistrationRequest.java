package org.secured.springmailrabbit.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;
}
