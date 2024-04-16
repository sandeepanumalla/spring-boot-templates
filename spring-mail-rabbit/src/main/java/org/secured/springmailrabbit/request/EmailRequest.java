package org.secured.springmailrabbit.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class EmailRequest implements Serializable {
    private String from;
    private String to;
    private String subject;
    private String body;
}
