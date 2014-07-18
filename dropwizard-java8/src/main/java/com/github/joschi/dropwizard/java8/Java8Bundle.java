package com.github.joschi.dropwizard.java8;

import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.github.joschi.dropwizard.java8.jersey.OptionalQueryParamInjectableProvider;
import com.github.joschi.dropwizard.java8.jersey.OptionalResourceMethodDispatchAdapter;
import com.github.joschi.dropwizard.java8.validation.valuehandling.OptionalValidatedValueUnwrapper;
import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.zapodot.jackson.java8.JavaOptionalModule;

public class Java8Bundle implements Bundle {
    @Override
    public void initialize(final Bootstrap<?> bootstrap) {
        bootstrap.getObjectMapper().registerModules(new JavaOptionalModule());
        bootstrap.getObjectMapper().registerModules(new JSR310Module());
    }

    @Override
    public void run(final Environment environment) {
        environment.jersey().register(OptionalQueryParamInjectableProvider.class);
        environment.jersey().register(OptionalResourceMethodDispatchAdapter.class);
        environment.jersey().register(OptionalValidatedValueUnwrapper.class);
    }
}
