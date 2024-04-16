package org.secured.springmailasync.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailRequest {
    private String from;
    private String to;
    private String body;
    private String subject;
}
