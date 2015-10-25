package io.dropwizard.java8.testing.junit;

import io.dropwizard.java8.jersey.OptionalMessageBodyWriter;
import io.dropwizard.java8.jersey.OptionalParamFeature;
import io.dropwizard.testing.junit.ResourceTestRule;

public class ResourceTestRuleBuilder {

    public static ResourceTestRule.Builder builder() {
        return new ResourceTestRule.Builder()
                .addProvider(OptionalMessageBodyWriter.class)
                .addProvider(OptionalParamFeature.class);
    }

}
