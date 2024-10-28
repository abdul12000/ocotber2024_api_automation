package stepDefs;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.Payload;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestfulBookerStepDef extends BaseSteps {
    Response responseForGetBooking, responseForGetBookingIds, responseForCreatBooking, responseForUpdateBooking, responseForDeleteBooking;

    @Given("I have an endpoint for retreiving a booking")
    public void i_have_an_endpoint_for_retreiving_a_booking() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I retrieve a bookingId {string}")
    public void i_retrieve_a_booking_id(String id) {
//        setHeadersWithContentType();
//        setEndpointPath(bookingEnpoint + id);
//      responseForGetBooking = getCall();


  //Using getCallWithoutHeader:
        setEndpointPath(bookingEndpoint + id);
        responseForGetBooking = getCallWithoutHeader();

    }

    @Then("firstname of {string}, lastname of {string} and statuscode of {int} are displayed")
    public void firstname_of_lastname_of_and_statuscode_of_are_displayed(String fName, String lName, Integer sCode) {
        // Write code here that turns the phrase above into concrete actions
assertThat(responseForGetBooking.statusCode(), equalTo(sCode));
        assertThat(responseForGetBooking.body().jsonPath().get("firstname"), equalTo(fName));
        assertThat(responseForGetBooking.body().jsonPath().get("lastname"), equalTo(lName));
    }

    @Given("I have an endpoint for retreiving bookingIds")
    public void i_have_an_endpoint_for_retreiving_booking_ids() {
        // Write code here that turns the phrase above into concrete actions


    }
    @When("I send request for getBookingIds")
    public void i_send_request_for_get_booking_ids() {
        // Write code here that turns the phrase above into concrete actions
        setEndpointPath(bookingEndpoint);
        responseForGetBookingIds = getCallWithoutHeader();
    }
    @Then("statuscode of {int} is returned")
    public void statuscode_of_is_returned(Integer sCode) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(responseForGetBookingIds.statusCode(), equalTo(sCode));
    }

    @Given("I have an endpoint for creating bookings")
    public void i_have_an_endpoint_for_creating_bookings() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("I send request to create a new booking with {string}, {string}, {string}, {string}, {string}, {string}  and {string}")
    public void i_send_request_to_create_a_new_booking_with_and(String fName, String lName, String tPrice, String depoPaid, String cIn, String cOut, String addNeeds) {
        // Write code here that turns the phrase above into concrete actions

        setHeadersWithContentTypeAndAccept();
        setEndpointPath(bookingEndpoint);
        Payload payload = new Payload();
        DocumentContext reqBody = loadJsonTemplate(creatBookingPayloadPath);
        payload.setPayloadForCreateABooking(reqBody,fName, lName, tPrice, depoPaid, cIn, cOut, addNeeds );

        responseForCreatBooking = getPostCall();


    }
    @Then("{string}, {string} and statuscode of {int} are returned")
    public void and_statuscode_of_are_returned(String fName, String lName, Integer sCode) {
        assertThat(responseForCreatBooking.statusCode(), equalTo(sCode));
        assertThat(responseForCreatBooking.body().jsonPath().get("booking.firstname"), equalTo(fName));
        assertThat(responseForCreatBooking.body().jsonPath().get("booking.lastname"), equalTo(lName));
    }

    @When("I send request to update a booking {string} with {string}, {string}, {string}, {string}, {string}, {string}  and {string}")
    public void iSendRequestToUpdateABookingWithAnd(String id, String fName, String lName, String tPrice, String depoPaid, String cIn, String cOut, String addNeeds) {
        setHeadersWithContentTypeAndAuth();
        setEndpointPath(bookingEndpoint + id);
        Payload payload = new Payload();
        DocumentContext reqBody = loadJsonTemplate(creatBookingPayloadPath);
        payload.setPayloadForCreateABooking(reqBody,fName, lName, tPrice, depoPaid, cIn, cOut, addNeeds );

        responseForUpdateBooking = getPutCall();
    }

    @Then("{string}, {string}, {string}, {string} and statuscode of {int} are returned for the updated booking")
    public void andStatuscodeOfAreReturnedForTheUpdatedBooking(String fName, String lName, String tPrice, String addNeeds, int sCode) {
        assertThat(responseForUpdateBooking.statusCode(), equalTo(sCode));
        assertThat(responseForUpdateBooking.body().jsonPath().get("firstname"), equalTo(fName));
        assertThat(responseForUpdateBooking.body().jsonPath().get("lastname"), equalTo(lName));
        assertThat(responseForUpdateBooking.body().jsonPath().get("totalprice"), equalTo(Integer.parseInt(tPrice)));
        assertThat(responseForUpdateBooking.body().jsonPath().get("additionalneeds"), equalTo(addNeeds));
    }



    @Given("I have an endpoint for deleting bookings")
    public void i_have_an_endpoint_for_deleting_bookings() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("I send request to delete a booking {string}")
    public void i_send_request_to_delete_a_booking(String id) {
        // Write code here that turns the phrase above into concrete actions
        setHeadersWithContentTypeAndAuth();
        setEndpointPath(bookingEndpoint + id);
        responseForDeleteBooking = deleteCall();
    }
    @Then("statuscode of {int} is returned for the delete booking")
    public void statuscode_of_is_returned_for_the_delete_booking(Integer sCode) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(responseForDeleteBooking.statusCode(), equalTo(sCode));
    }


    @When("I send request to delete a booking")
    public void iSendRequestToDeleteABooking() {

    }
}
