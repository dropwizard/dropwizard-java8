package io.dropwizard.java8.jdbi.args;

import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LocalDateMapperTest {

    private final ResultSet resultSet = Mockito.mock(ResultSet.class);

    @Test
    public void extractByName() throws Exception {
        when(resultSet.getTimestamp("name")).thenReturn(Timestamp.valueOf("2007-12-03 00:00:00.000"));

        LocalDate actual = new LocalDateMapper().extractByName(resultSet, "name");

        assertThat(actual).isEqualTo(LocalDate.parse("2007-12-03"));
    }

    @Test
    public void extractByName_TimestampIsNull() throws Exception {
        when(resultSet.getTimestamp("name")).thenReturn(null);

        LocalDate actual = new LocalDateMapper().extractByName(resultSet, "name");

        assertThat(actual).isNull();
    }

    @Test
    public void extractByIndex() throws Exception {
        when(resultSet.getTimestamp(1)).thenReturn(Timestamp.valueOf("2007-12-03 00:00:00.000"));

        LocalDate actual = new LocalDateMapper().extractByIndex(resultSet, 1);

        assertThat(actual).isEqualTo(LocalDate.parse("2007-12-03"));
    }

    @Test
    public void extractByIndex_TimestampIsNull() throws Exception {
        when(resultSet.getTimestamp(1)).thenReturn(null);

        LocalDate actual = new LocalDateMapper().extractByIndex(resultSet, 1);

        assertThat(actual).isNull();
    }
}