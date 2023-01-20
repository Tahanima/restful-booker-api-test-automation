package io.github.tahanima.api;

import io.github.tahanima.api.payload.AuthRequestPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.github.tahanima.util.ApiEndpoints.AUTH_ENDPOINT;
import static io.github.tahanima.util.ApiEndpoints.BASE_URL;
import static io.restassured.RestAssured.given;

/**
 * @author tahanima
 */
public final class AuthApi {
    private AuthApi() {}

    public static Response createToken(AuthRequestPayload authRequestPayload) {
        return given().contentType(ContentType.JSON)
                .body(authRequestPayload)
                .when()
                .post(BASE_URL + AUTH_ENDPOINT);
    }
}
