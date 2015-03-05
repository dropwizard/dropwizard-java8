package io.dropwizard.java8.jersey;

public class MyMessage {
    private final String message;

    public MyMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
