package io.dropwizard.java8.jersey.params;

import io.dropwizard.jersey.params.AbstractParam;

import java.time.ZoneId;

/**
 * A parameter encapsulating time-zone IDs, such as Europe/Paris.
 * All non-parsable values will return a {@code 400 Bad Request} response.
 *
 * @see java.time.ZoneId
 */
public class ZoneIdParam extends AbstractParam<ZoneId> {
    public ZoneIdParam(final String input) {
        super(input);
    }

    @Override
    protected ZoneId parse(final String input) throws Exception {
        return ZoneId.of(input);
    }
}
