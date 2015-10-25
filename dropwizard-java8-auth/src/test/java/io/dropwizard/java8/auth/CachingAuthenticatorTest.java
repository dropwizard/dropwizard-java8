package io.dropwizard.java8.auth;

import com.codahale.metrics.MetricRegistry;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.PrincipalImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.Principal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CachingAuthenticatorTest {
    @Mock
    private Authenticator<String, Principal> underlying;
    private CachingAuthenticator<String, Principal> cached;

    @Before
    public void setUp() throws Exception {
        when(underlying.authenticate(anyString())).thenReturn(Optional.<Principal>of(new PrincipalImpl("principal")));
        cached = new CachingAuthenticator<>(new MetricRegistry(), underlying, CacheBuilderSpec.parse("maximumSize=1"));

    }

    @Test
    public void cachesTheFirstReturnedPrincipal() throws Exception {
        assertThat(cached.authenticate("credentials")).isEqualTo(Optional.<Principal>of(new PrincipalImpl("principal")));
        assertThat(cached.authenticate("credentials")).isEqualTo(Optional.<Principal>of(new PrincipalImpl("principal")));

        verify(underlying, times(1)).authenticate("credentials");
    }

    @Test
    public void respectsTheCacheConfiguration() throws Exception {
        cached.authenticate("credentials1");
        cached.authenticate("credentials2");
        cached.authenticate("credentials1");

        final InOrder inOrder = inOrder(underlying);
        inOrder.verify(underlying, times(1)).authenticate("credentials1");
        inOrder.verify(underlying, times(1)).authenticate("credentials2");
        inOrder.verify(underlying, times(1)).authenticate("credentials1");
    }

    @Test
    public void invalidatesSingleCredentials() throws Exception {
        cached.authenticate("credentials");
        cached.invalidate("credentials");
        cached.authenticate("credentials");

        verify(underlying, times(2)).authenticate("credentials");
    }

    @Test
    public void invalidatesSetsOfCredentials() throws Exception {
        cached.authenticate("credentials");
        cached.invalidateAll(ImmutableSet.of("credentials"));
        cached.authenticate("credentials");

        verify(underlying, times(2)).authenticate("credentials");
    }

    @Test
    public void invalidatesCredentialsMatchingGivenPredicate() throws Exception {
        cached.authenticate("credentials");
        cached.invalidateAll(c -> c.equals("credentials"));
        cached.authenticate("credentials");

        verify(underlying, times(2)).authenticate("credentials");
    }

    @Test
    public void invalidatesAllCredentials() throws Exception {
        cached.authenticate("credentials");
        cached.invalidateAll();
        cached.authenticate("credentials");

        verify(underlying, times(2)).authenticate("credentials");
    }

    @Test
    public void calculatesTheSizeOfTheCache() throws Exception {
        cached.authenticate("credentials1");
        assertThat(cached.size()).isEqualTo(1);
    }

    @Test
    public void calculatesCacheStats() throws Exception {
        cached.authenticate("credentials1");
        assertThat(cached.stats().loadCount()).isEqualTo(0);
        assertThat(cached.size()).isEqualTo(1);
    }

    @Test
    public void shouldNotCacheAbsentPrincipals() throws Exception {
        when(underlying.authenticate(anyString())).thenReturn(Optional.<Principal>empty());
        assertThat(cached.authenticate("credentials")).isEqualTo(Optional.empty());
        verify(underlying).authenticate("credentials");
        assertThat(cached.size()).isEqualTo(0);
    }
}
