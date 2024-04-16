package org.secured.springmaildemo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestDto {

    private String name;
    private String email;
    private String password;

}
