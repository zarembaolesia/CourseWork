import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.main.automation.base.BaseTestNG;
import org.main.automation.utils.BookingRequest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestEx extends BaseTestNG {

    @Test
    public void getBookingIdTest () {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .body("bookingid", is(notNullValue()));
    }
    @DataProvider
    public Object[][] createBookingData() {
        return new Object[][]{
                {new BookingRequest("Tabby", "Dunphy", 121, true, new BookingRequest.BookingDates(LocalDate.of(2018, 1, 1), LocalDate.of(2019, 1, 1)), "Supper")},
                {new BookingRequest("", "", 121, false, new BookingRequest.BookingDates(LocalDate.of(1, 1, 1), LocalDate.of(1, 1, 1)), "")},
        };
    }

    @Test(dataProvider = "createBookingData")
    public void createBookingTest (BookingRequest bookingRequest) {
        RestAssured.given()
                .header(new Header("Content-Type", "application/json"))
                .header(new Header("Accept",    "application/json"))
                .body(bookingRequest)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .body("bookingid", is(notNullValue()))
                .body("booking.firstname", equalTo(bookingRequest.getFirstname()))
                .body("booking.lastname", equalTo(bookingRequest.getLastname()))
                .body("booking.totalprice", equalTo(bookingRequest.getTotalprice()))
                .body("booking.depositpaid", equalTo(bookingRequest.isDepositpaid()))
                .body("booking.bookingdates.checkin", equalTo(bookingRequest.getBookingdates().getCheckin()))
                .body("booking.bookingdates.checkout", equalTo(bookingRequest.getBookingdates().getCheckout()))
                .body("booking.additionalneeds", equalTo(bookingRequest.getAdditionalneeds()));
    }

    private String token;

    @DataProvider
    public Object[][] updateBookingData() {
        return new Object[][]{
                {new BookingRequest("Helen", "Karr", 121, true, new BookingRequest.BookingDates(LocalDate.of(2018, 1, 1), LocalDate.of(2019, 1, 1)), "Supper")},
        };
    }

    @BeforeTest
    public void auth (){
        Response response = RestAssured.given()
                .header(new Header("Content-Type", "application/json"))
                .body("{\"username\": \"admin\", \"password\": \"password123\"}")
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200)
                .body("token", is(notNullValue()))
                .extract().response();

        token = response.jsonPath().getString("token");
    }

    @Test
    public void partialUpdateBookingTest () {
        RestAssured.given()
                .header(new Header("Content-Type", "application/json"))
                .header(new Header("Accept",    "application/json"))
                .header(new Header("Cookie", "token="+token))
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .body("{\"totalprice\": 12670}")
                .pathParam("id", 12)
                .when()
                .patch("/{id}")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Smith"))
                .body("totalprice", equalTo(12670))
                .body("depositpaid", equalTo(true))
                .body("additionalneeds", equalTo("Breakfast"));
    }

    @Test(dataProvider = "updateBookingData")
    public void UpdateBookingTest (BookingRequest bookingRequest) {
        RestAssured.given()
                .header(new Header("Content-Type", "application/json"))
                .header(new Header("Accept",    "application/json"))
                .header(new Header("Cookie", "token="+token))
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .body(bookingRequest)
                .pathParam("id", 3)
                .when()
                .put("/{id}")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Helen"))
                .body("lastname", equalTo("Karr"))
                .body("totalprice", equalTo(121))
                .body("depositpaid", equalTo(true))
                .body("additionalneeds", equalTo("Supper"));
    }

    @Test
    public void deleteBookingTest () {
        RestAssured.given()
                .header(new Header("Content-Type", "application/json"))
                .header(new Header("Accept",    "application/json"))
                .header(new Header("Cookie", "token="+token))
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .pathParam("id", 5)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(201);

        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/5")
                .then()
                .statusCode(404);
    }
}
