package api_test_automation_with_restAssured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class RestAssuredApiTests {

    @Test
    public void
    getBookingIds() {
        given().log().all().
                when().
                get("https://restful-booker.herokuapp.com/booking").
                then().log().all().
                statusCode(200).
                body("bookingid", hasItems(1537, 3037));

    }

    @Test
    public void getBooking() {
        given().log().all().
                when().get("https://restful-booker.herokuapp.com/booking/880").
                then().log().all().statusCode(200).body("firstname", equalTo("Josh")).body("lastname", equalTo("Allen"));

    }

    @Test
    public void CreateBooking() {
        HashMap<String, Object> bookingPayload = new HashMap<>();
        //Add the attributes:
        bookingPayload.put("firstname", "Esther");
        bookingPayload.put("lastname", "Abdulsalam");
        bookingPayload.put("totalprice", 15510);
        bookingPayload.put("depositpaid", false);
        bookingPayload.put("additionalneeds", "Burg Khalifah and Breakfast");

        HashMap<String,String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-04-01");
        bookingDates.put("checkout", "2025-05-01");


        bookingPayload.put("bookingdates", bookingDates);

        given().log().all().contentType(ContentType.JSON).body(bookingPayload).
                when().post("https://restful-booker.herokuapp.com/booking").
                then().log().all().statusCode(200).body("booking.firstname", equalTo("Esther")).body("booking.lastname", equalTo("Abdulsalam"));

    }

    @Test
    public void CreateToken() {
        HashMap<String, Object> payload = new HashMap<>();
        //Add the attributes:
        payload.put("username", "admin");
        payload.put("password", "password123");

        given().log().all().contentType(ContentType.JSON).body(payload).
                when().post("https://restful-booker.herokuapp.com/auth").
                then().log().all().statusCode(200);
    }
    @Test
    public void UpdateBookingWithPutUsingToken() {
        HashMap<String, Object> bookingPayload = new HashMap<>();
        //Add the attributes:
        bookingPayload.put("firstname", "Esther");
        bookingPayload.put("lastname", "Ade");
        bookingPayload.put("totalprice", 60010);
        bookingPayload.put("depositpaid", true);
        bookingPayload.put("additionalneeds", "Burg Khalifah and Dinner");

        HashMap<String,String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-04-01");
        bookingDates.put("checkout", "2025-05-01");


        bookingPayload.put("bookingdates", bookingDates);

        given().log().all().contentType(ContentType.JSON).header("Cookie", "token=accfa4d1272525d").body(bookingPayload).
                when().put("https://restful-booker.herokuapp.com/booking/607").
                then().log().all().statusCode(200).body("firstname", equalTo("Esther")).body("lastname", equalTo("Ade"));

    }

    @Test
    public void UpdateBookingWithPutUsingAuth() {
        HashMap<String, Object> bookingPayload = new HashMap<>();
        //Add the attributes:
        bookingPayload.put("firstname", "Esther");
        bookingPayload.put("lastname", "Ade");
        bookingPayload.put("totalprice", 60010);
        bookingPayload.put("depositpaid", true);
        bookingPayload.put("additionalneeds", "Burg Khalifah and Dinner");

        HashMap<String,String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-04-01");
        bookingDates.put("checkout", "2025-05-01");


        bookingPayload.put("bookingdates", bookingDates);

        given().log().all().contentType(ContentType.JSON).header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=").body(bookingPayload).
                when().put("https://restful-booker.herokuapp.com/booking/1122").
                then().log().all().statusCode(200).body("firstname", equalTo("Esther")).body("lastname", equalTo("Ade"));

    }

    @Test
    public void deleteBooking() {
        given().log().all().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=").
                when().
                delete("https://restful-booker.herokuapp.com/booking/550").
                then().log().all().
                statusCode(201);

    }

}
