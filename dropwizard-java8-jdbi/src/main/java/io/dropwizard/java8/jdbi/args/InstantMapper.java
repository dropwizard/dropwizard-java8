package io.dropwizard.java8.jdbi.args;

import org.skife.jdbi.v2.util.TypedMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.TimeZone;

/**
 * A {@link TypedMapper} to map {@link Instant} objects.
 */
public class InstantMapper extends TypedMapper<Instant> {
    /**
     * <p>{@link Calendar} for representing a database time zone.<p>
     * If a field is not represented in a database as
     * {@code TIMESTAMP WITH TIME ZONE}, we need to set its time zone
     * explicitly. Otherwise it will not be correctly represented in
     * a time zone different from the time zone of the database.
     */
    private final Optional<Calendar> calendar;

    public InstantMapper() {
        this(Optional.empty());
    }

    public InstantMapper(final Optional<TimeZone> tz) {
        this.calendar = tz.map(GregorianCalendar::new);
    }

    @Override
    protected Instant extractByName(ResultSet r, String name) throws SQLException {
        final Timestamp timestamp = calendar.isPresent() ? r.getTimestamp(name, cloneCalendar()) : r.getTimestamp(name);
        return timestamp == null ? null : timestamp.toInstant();
    }

    @Override
    protected Instant extractByIndex(ResultSet r, int index) throws SQLException {
        final Timestamp timestamp = calendar.isPresent() ? r.getTimestamp(index, cloneCalendar()) : r.getTimestamp(index);
        return timestamp == null ? null : timestamp.toInstant();
    }

    private Calendar cloneCalendar() {
        return (Calendar) calendar.get().clone();
    }
}
