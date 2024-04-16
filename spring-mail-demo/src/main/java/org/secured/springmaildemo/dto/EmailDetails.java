package org.secured.springmaildemo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailDetails {
    private String to;
    private String subject;
    private String body;
}
