package io.dropwizard.java8.jdbi.args;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;

/**
 * An {@link Argument} for {@link Instant} objects.
 */
public class InstantArgument implements Argument {

    private Instant instant;

    protected InstantArgument(Instant instant) {
        this.instant = instant;
    }

    @Override
    public void apply(int position, PreparedStatement statement, StatementContext ctx) throws SQLException {
        if (instant != null) {
            statement.setTimestamp(position, Timestamp.from(instant));
        } else {
            statement.setNull(position, Types.TIMESTAMP);
        }
    }
}
