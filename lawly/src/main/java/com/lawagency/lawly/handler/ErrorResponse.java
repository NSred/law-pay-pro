package com.lawagency.lawly.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String title;

    public ErrorResponse() {
        this.title = "Law agency application";
    }
}
