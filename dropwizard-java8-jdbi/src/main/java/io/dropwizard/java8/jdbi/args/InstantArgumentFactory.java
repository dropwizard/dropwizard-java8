package io.dropwizard.java8.jdbi.args;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

import java.time.Instant;

/**
 * An {@link ArgumentFactory} for {@link Instant} arguments.
 */
public class InstantArgumentFactory implements ArgumentFactory<Instant> {

    @Override
    public boolean accepts(Class<?> expectedType, Object value, StatementContext ctx) {
        return value instanceof Instant;
    }

    @Override
    public Argument build(Class<?> expectedType, Instant value, StatementContext ctx) {
        return new InstantArgument(value);
    }
}
