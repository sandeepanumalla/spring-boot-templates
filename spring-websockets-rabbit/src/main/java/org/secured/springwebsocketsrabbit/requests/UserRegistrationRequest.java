package org.secured.springwebsocketsrabbit.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegistrationRequest {
    private String username;
    private String name;
    private String email;
    private String password;
}
