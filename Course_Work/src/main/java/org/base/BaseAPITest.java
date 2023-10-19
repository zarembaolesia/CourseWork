package org.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.utils.Config;
import org.utils.CourseJSONRPC;

public class BaseAPITest {
    final protected CourseJSONRPC jsonRpcPayload = new CourseJSONRPC();

    public RequestSpecification request() {
        return this.request(null, null);
    }

    public RequestSpecification request(String username, String password) {
        username = username != null ? username : Config.API_USERNAME.value;
        password = password != null ? password : Config.API_PASSWORD.value;

        return RestAssured.given()
                .baseUri(Config.getApiBaseURL().toString())
                .auth()
                .basic(username, password);
    }
}
