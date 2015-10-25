package io.dropwizard.java8.testing.junit;

import io.dropwizard.java8.testing.junit.TestResource.Check;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.ClientResponseContext;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ResourceTestRuleBuilderTest {

    Check check = mock(Check.class);

    @Rule
    public ResourceTestRule testRule = ResourceTestRuleBuilder.builder().addResource(new TestResource(check)).build();

    @Test
    public void testOptionalParamsNotPresent() {
        String result = testRule.client()
                .target("/optional/params")
                .request()
                .get(String.class);

        verify(check, times(1)).check(empty(), empty());
        assertEquals("{}", result);
    }

    @Test
    public void testOptionalParamsPresent() {
        String result = testRule.client()
                .target("/optional/params")
                .queryParam("p1", "One")
                .queryParam("p2", 2)
                .request()
                .get(String.class);

        verify(check, times(1)).check(of("One"), of(2l));
        assertEquals("{}", result);
    }

    @Test
    public void testOptionalSingleParamPresent() {
        String result = testRule.client()
                .target("/optional/params")
                .queryParam("p2", 2)
                .request()
                .get(String.class);

        verify(check, times(1)).check(empty(), of(2l));
        assertEquals("{}", result);
    }

    @Test
    public void testOptionalResponseSome() {
        String result = testRule.client()
                .target("/optional/some")
                .request()
                .get(String.class);

        assertEquals("{}", result);
    }

    @Test(expected = javax.ws.rs.NotFoundException.class)
    public void testOptionalResponseEmpty() {
        testRule.client()
                .target("/optional/empty")
                .request()
                .get(ClientResponseContext.class);
    }

}