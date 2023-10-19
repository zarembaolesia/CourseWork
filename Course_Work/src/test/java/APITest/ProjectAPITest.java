package APITest;

import io.restassured.http.ContentType;
import org.base.BaseAPITest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProjectAPITest extends BaseAPITest {

    @Test(groups= {"ProjectTest"})
    public void createProjectTest() {
        String createProjectBody = jsonRpcPayload.project.createProject("Project1").toString();
        String response = this.request()
                .contentType(ContentType.JSON)
                .body(createProjectBody)
                .post("/jsonrpc.php")
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(response.contains("Project1"));
    }

    @Test(groups= {"ProjectTest"})
    public void deleteProjectTest() {
        String deleteProjectBody = jsonRpcPayload.project.deleteProject("Project1").getAsString();
        String response = this.request()
                .contentType(ContentType.JSON)
                .body(deleteProjectBody)
                .post("/jsonrpc.php")
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(response.contains("true"));
    }
}
