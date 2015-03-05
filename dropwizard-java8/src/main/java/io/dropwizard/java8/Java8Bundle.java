package io.dropwizard.java8;

import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.dropwizard.Bundle;
import io.dropwizard.java8.jersey.OptionalMessageBodyWriter;
import io.dropwizard.java8.jersey.OptionalParamFeature;
import io.dropwizard.java8.validation.valuehandling.OptionalValidatedValueUnwrapper;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.HibernateValidator;
import org.zapodot.jackson.java8.JavaOptionalModule;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class Java8Bundle implements Bundle {
    @Override
    public void initialize(final Bootstrap<?> bootstrap) {
        bootstrap.getObjectMapper().registerModules(new JavaOptionalModule());
        bootstrap.getObjectMapper().registerModules(new JSR310Module());

        final ValidatorFactory validatorFactory = Validation
                .byProvider(HibernateValidator.class)
                .configure()
                .addValidatedValueHandler(new io.dropwizard.validation.valuehandling.OptionalValidatedValueUnwrapper())
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
