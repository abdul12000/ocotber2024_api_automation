package utilities;

import com.jayway.jsonpath.DocumentContext;

public class Payload {
    public void setPayloadForCreateABooking(DocumentContext requestBody, String fName, String lName, String tPrice, String depoPaid, String cIn, String cOut, String addNeeds ){
        requestBody.set("firstname", fName);
        requestBody.set("lastname", lName);
        requestBody.set("totalprice", tPrice);
        requestBody.set("depositpaid", depoPaid);
        requestBody.set("bookingdates.checkin", cIn);
        requestBody.set("bookingdates.checkout", cOut);
        requestBody.set("additionalneeds", addNeeds);
    }
}
