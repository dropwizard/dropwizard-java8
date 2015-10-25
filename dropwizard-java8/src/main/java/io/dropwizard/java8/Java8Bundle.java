package io.dropwizard.java8;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.dropwizard.Bundle;
import io.dropwizard.java8.jersey.OptionalMessageBodyWriter;
import io.dropwizard.java8.jersey.OptionalParamFeature;
import io.dropwizard.java8.validation.valuehandling.OptionalValidatedValueUnwrapper;
import io.dropwizard.jersey.validation.Validators;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.validation.ValidatorFactory;

public class Java8Bundle implements Bundle {
    @Override
    public void initialize(final Bootstrap<?> bootstrap) {
        bootstrap.getObjectMapper().registerModules(new Jdk8Module());
        bootstrap.getObjectMapper().registerModules(new JavaTimeModule());

        final ValidatorFactory validatorFactory = Validators.newConfiguration()
                .addValidatedValueHandler(new OptionalValidatedValueUnwrapper())
                .buildValidatorFactory();
        bootstrap.setValidatorFactory(validatorFactory);
    }

    @Override
    public void run(final Environment environment) {
        environment.jersey().register(OptionalMessageBodyWriter.class);
        environment.jersey().register(OptionalParamFeature.class);
    }
}
