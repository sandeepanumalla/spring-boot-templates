package org.secured.springwebsocketsrabbit.requests;

import lombok.Builder;

@Builder
public class Notification {

    private String recipient;
    private String type;
    private String message;

}
