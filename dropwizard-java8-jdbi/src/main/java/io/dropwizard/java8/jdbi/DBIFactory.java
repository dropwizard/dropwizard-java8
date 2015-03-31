package io.dropwizard.java8.jdbi;

import io.dropwizard.java8.jdbi.args.LocalDateArgumentFactory;
import io.dropwizard.java8.jdbi.args.LocalDateMapper;
import io.dropwizard.java8.jdbi.args.LocalDateTimeArgumentFactory;
import io.dropwizard.java8.jdbi.args.LocalDateTimeMapper;
import io.dropwizard.java8.jdbi.args.OptionalArgumentFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class DBIFactory extends io.dropwizard.jdbi.DBIFactory {
    @Override
    public DBI build(Environment environment, DataSourceFactory configuration, ManagedDataSource dataSource, String name) {
        final DBI dbi = super.build(environment, configuration, dataSource, name);

        dbi.registerArgumentFactory(new OptionalArgumentFactory(configuration.getDriverClass()));
        dbi.registerContainerFactory(new OptionalContainerFactory());
        dbi.registerArgumentFactory(new LocalDateArgumentFactory());
        dbi.registerArgumentFactory(new LocalDateTimeArgumentFactory());
        dbi.registerMapper(new LocalDateMapper());
        dbi.registerMapper(new LocalDateTimeMapper());

        return dbi;
    }
}
