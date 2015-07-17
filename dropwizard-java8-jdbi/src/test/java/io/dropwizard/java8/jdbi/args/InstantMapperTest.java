package io.dropwizard.java8.jdbi.args;

import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class InstantMapperTest {

    private final ResultSet resultSet = Mockito.mock(ResultSet.class);

    @Test
    public void extractByName() throws Exception {
        ZonedDateTime expected = ZonedDateTime.parse("2012-12-21T00:00:00.000Z");
        ZonedDateTime stored = expected.withZoneSameInstant(ZoneId.systemDefault());
        when(resultSet.getTimestamp("instant")).thenReturn(Timestamp.from(stored.toInstant()));

        Instant actual = new InstantMapper().extractByName(resultSet, "instant");

        assertThat(actual).isEqualTo(expected.toInstant());
    }

    @Test
    public void extractByName_TimestampIsNull() throws Exception {
        when(resultSet.getTimestamp("instant")).thenReturn(null);

        Instant actual = new InstantMapper().extractByName(resultSet, "instant");

        assertThat(actual).isNull();
    }

    @Test
    public void extractByIndex() throws Exception {
        ZonedDateTime expected = ZonedDateTime.parse("2012-12-21T00:00:00.000Z");
        ZonedDateTime stored = expected.withZoneSameInstant(ZoneId.systemDefault());
        when(resultSet.getTimestamp(1)).thenReturn(Timestamp.from(stored.toInstant()));

        Instant actual = new InstantMapper().extractByIndex(resultSet, 1);

        assertThat(actual).isEqualTo(expected.toInstant());
    }

    @Test
    public void extractByIndex_TimestampIsNull() throws Exception {
        when(resultSet.getTimestamp(1)).thenReturn(null);

        Instant actual = new InstantMapper().extractByIndex(resultSet, 1);

        assertThat(actual).isNull();
    }
}
