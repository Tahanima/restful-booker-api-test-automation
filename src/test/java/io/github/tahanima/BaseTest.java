package io.github.tahanima;

import io.github.tahanima.api.AuthApi;
import io.github.tahanima.api.PingApi;
import io.github.tahanima.api.payload.AuthRequestPayload;
import io.github.tahanima.api.payload.AuthResponsePayload;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.emptyString;

/**
 * @author tahanima
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseTest {
    protected String token;
    protected final Faker faker = new Faker();

    @BeforeAll
    void testHealthCheckReturns201() {
        Response response = PingApi.healthCheck();

        assertThat(response.statusCode(), equalTo(SC_CREATED));
    }

    @BeforeEach
    void testCreateTokenReturns200() {
        AuthRequestPayload authRequestPayload
                = AuthRequestPayload.builder()
                .username("admin")
                .password("password123")
                .build();

        Response response = AuthApi.createToken(authRequestPayload);
        token = response.as(AuthResponsePayload.class).getToken();

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testCreateTokenReturnsNonEmptyToken() {
        assertThat(token, is(not(emptyString())));
    }
}
