package io.dropwizard.java8.jdbi.args;

import org.junit.Test;
import org.mockito.Mockito;
import org.skife.jdbi.v2.StatementContext;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantArgumentTest {

    private final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private final StatementContext context = Mockito.mock(StatementContext.class);

    @Test
    public void apply() throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2012-12-21T00:00:00.000Z");
        ZonedDateTime expected = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault());

        new InstantArgument(zonedDateTime.toInstant()).apply(1, statement, context);

        Mockito.verify(statement).setTimestamp(1, Timestamp.from(expected.toInstant()));
    }

    @Test
    public void apply_ValueIsNull() throws Exception {
        new InstantArgument(null).apply(1, statement, context);

        Mockito.verify(statement).setNull(1, Types.TIMESTAMP);
    }
}
