package io.dropwizard.java8.jdbi;

import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.java8.jdbi.args.InstantArgumentFactory;
import io.dropwizard.java8.jdbi.args.InstantMapper;
import io.dropwizard.java8.jdbi.args.LocalDateArgumentFactory;
import io.dropwizard.java8.jdbi.args.LocalDateMapper;
import io.dropwizard.java8.jdbi.args.LocalDateTimeArgumentFactory;
import io.dropwizard.java8.jdbi.args.LocalDateTimeMapper;
import io.dropwizard.java8.jdbi.args.OptionalArgumentFactory;
import io.dropwizard.java8.jdbi.args.OptionalInstantArgumentFactory;
import io.dropwizard.java8.jdbi.args.OptionalLocalDateArgumentFactory;
import io.dropwizard.java8.jdbi.args.OptionalLocalDateTimeArgumentFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.Optional;
import java.util.TimeZone;

public class DBIFactory extends io.dropwizard.jdbi.DBIFactory {
    @Override
    public DBI build(Environment environment,
                     PooledDataSourceFactory configuration,
                     ManagedDataSource dataSource,
                     String name) {
        final DBI dbi = super.build(environment, configuration, dataSource, name);

        dbi.registerArgumentFactory(new OptionalArgumentFactory(configuration.getDriverClass()));
        dbi.registerContainerFactory(new OptionalContainerFactory());
        dbi.registerArgumentFactory(new LocalDateArgumentFactory());
        dbi.registerArgumentFactory(new OptionalLocalDateArgumentFactory());
        dbi.registerArgumentFactory(new LocalDateTimeArgumentFactory());
        dbi.registerArgumentFactory(new OptionalLocalDateTimeArgumentFactory());
        dbi.registerMapper(new LocalDateMapper());
        dbi.registerMapper(new LocalDateTimeMapper());

        final Optional<TimeZone> tz = Optional.ofNullable(databaseTimeZone().orNull());
        dbi.registerArgumentFactory(new InstantArgumentFactory(tz));
        dbi.registerArgumentFactory(new OptionalInstantArgumentFactory(tz));
        dbi.registerMapper(new InstantMapper(tz));


        return dbi;
    }
}
