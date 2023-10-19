package APITest;

import io.restassured.http.ContentType;
import org.base.BaseAPITest;
import org.base.Project;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TaskAPITest extends BaseAPITest {

    @Test(groups= {"TaskTest"})
    public void createTaskTest() {
        String createProjectBody = jsonRpcPayload.project.createProject("Project2").getAsString();
        Project projectResponse = this.request()
                .contentType(ContentType.JSON)
                .body(createProjectBody)
                .post("/jsonrpc.php")
                .then()
                .statusCode(200)
                .extract().body().jsonPath( ).getObject("projectResponse", Project.class);;

        String createTaskBody = jsonRpcPayload.task.createTask("Task2", projectResponse.getId()).getAsString();
        String taskResponse = this.request()
                .contentType(ContentType.JSON)
                .body(createTaskBody)
                .post("/jsonrpc.php")
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(taskResponse.contains("Task2"));
    }

    @Test(groups= {"TaskTest"})
    public void deletTaskTest() {
        String deletTasktBody = jsonRpcPayload.task.deleteTask("Task2").getAsString();
        String taskResponse = this.request()
                .contentType(ContentType.JSON)
                .body(deletTasktBody)
                .post("/jsonrpc.php")
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(taskResponse.contains("true"));
    }
}
