package org.base;

import io.restassured.http.ContentType;

public class APISetup extends BaseAPITest {

    public String createUser(String userName, String password, String role) {
        String userBody = jsonRpcPayload.user.createUser(userName, password, role).toString();
        User response = this.request( )
                .contentType(ContentType.JSON)
                .body(userBody)
                .post("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).as(User.class);

        return response.getUserName();
    }

    public String createProject(String projectName) {
        String createProjectBody = jsonRpcPayload.project.createProject(projectName).getAsString();
        Project response = this.request()
                .contentType(ContentType.JSON)
                .body(createProjectBody)
                .post("/jsonrpc.php")
                .then()
                .statusCode(200)
                .extract().as(Project.class);

        return response.getName();
    }
}
