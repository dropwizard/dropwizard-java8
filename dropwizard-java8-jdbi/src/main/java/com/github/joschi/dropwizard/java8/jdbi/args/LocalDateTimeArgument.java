package com.github.joschi.dropwizard.java8.jdbi.args;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * An {@link Argument} for {@link LocalDateTime} objects.
 */
public class LocalDateTimeArgument implements Argument {

    private final LocalDateTime value;

    LocalDateTimeArgument(final LocalDateTime value) {
        this.value = value;
    }

    @Override
    public void apply(final int position,
                      final PreparedStatement statement,
                      final StatementContext ctx) throws SQLException {
        if (value != null) {
            statement.setTimestamp(position, new Timestamp(value.toEpochSecond(ZoneOffset.UTC)));
        } else {
            statement.setNull(position, Types.TIMESTAMP);
        }
    }
}
