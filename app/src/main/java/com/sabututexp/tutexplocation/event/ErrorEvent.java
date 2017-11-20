package com.sabututexp.tutexplocation.event;

/**
 * Created by s on 20/11/17.
 */

public class ErrorEvent {
    private final String errorMessage;

    public ErrorEvent(String string) {
        this.errorMessage = string;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

