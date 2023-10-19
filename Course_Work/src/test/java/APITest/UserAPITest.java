package APITest;

import io.restassured.http.ContentType;
import org.base.BaseAPITest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserAPITest extends BaseAPITest {

    @Test
    public void createUserTest() {
        String createUserBody = jsonRpcPayload.user.createUser("user1", "user1", "admin").getAsString();
        String response = this.request()
                .contentType(ContentType.JSON)
                .body(createUserBody)
                .post("/jsonrpc.php")
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(response.contains("user1"));
    }

    @Test
    public void deleteUserTest() {
        String deleteUserBody = jsonRpcPayload.user.deleteUser("user1").getAsString();
        String response = this.request()
                .contentType(ContentType.JSON)
                .body(deleteUserBody)
                .post("/jsonrpc.php")
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(response.contains("true"));
    }
}
