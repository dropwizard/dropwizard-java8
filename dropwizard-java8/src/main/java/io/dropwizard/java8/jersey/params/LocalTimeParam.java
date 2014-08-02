package io.dropwizard.java8.jersey.params;

import io.dropwizard.jersey.params.AbstractParam;

import java.time.LocalTime;

/**
 * A parameter encapsulating time values. All non-parsable values will return a {@code 400 Bad
 * Request} response.
 *
 * @see java.time.LocalTime
 */
public class LocalTimeParam extends AbstractParam<LocalTime> {
    public LocalTimeParam(final String input) {
        super(input);
    }

    @Override
    protected LocalTime parse(final String input) throws Exception {
        return LocalTime.parse(input);
    }
}
