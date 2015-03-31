package io.dropwizard.java8.jdbi.args;

import org.skife.jdbi.v2.util.TypedMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class LocalDateMapper extends TypedMapper<LocalDate> {

    @Override
    protected LocalDate extractByName(ResultSet r, String name) throws SQLException {
        Timestamp timestamp = r.getTimestamp(name);
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    @Override
    protected LocalDate extractByIndex(ResultSet r, int index) throws SQLException {
        Timestamp timestamp = r.getTimestamp(index);
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
