package io.dropwizard.java8.jdbi.args;

import org.skife.jdbi.v2.util.TypedMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A {@link TypedMapper} to map {@link Instant} objects.
 */
public class InstantMapper extends TypedMapper<Instant> {

    @Override
    protected Instant extractByName(ResultSet r, String name) throws SQLException {
        Timestamp timestamp = r.getTimestamp(name);
        if (timestamp == null) {
            return null;
        }
        return timestamp.toInstant();
    }

    @Override
    protected Instant extractByIndex(ResultSet r, int index) throws SQLException {
        Timestamp timestamp = r.getTimestamp(index);
        if (timestamp == null) {
            return null;
        }
        return timestamp.toInstant();
    }
}
