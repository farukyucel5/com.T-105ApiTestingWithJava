package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {
    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
    	                Request body
    	           {
    	                "firstname" : "Ahmet",
    	                "lastname" : “Bulut",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }


    	            	Response Body
    	           {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                             }
                    }
         */
    @Test
    public void post01(){

        // 1 - URL ve body hazirla

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject innerBody = new JSONObject();

        innerBody.put("checkin", "2021-06-01");
        innerBody.put("checkout", "2021-06-10");

        JSONObject reqBody = new JSONObject();

        reqBody.put("firstname" , "Ali");
        reqBody.put("lastname" , "Bak");
        reqBody.put("totalprice" , 500);
        reqBody.put("depositpaid" , false);
        reqBody.put("bookingdates" ,innerBody);
        reqBody.put("additionalneeds" , "wi-fi");

        // System.out.println("reqBody = " + reqBody);

        // 2 - Expected Data hazirla

        JSONObject expBody = new JSONObject();

        expBody.put("bookingid",24);
        expBody.put("booking",reqBody);

        // System.out.println("expBody = " + expBody);

        // 3 - Response'i kaydet

        Response response = given().
                                    contentType(ContentType.JSON).
                            when().
                                    body(reqBody.toString()).
                                    post(url);

        // System.out.println("response = ");
        // response.prettyPrint();

        // 4 - Assertion

        JsonPath resJsonPath = response.jsonPath();

        assertEquals(expBody.getJSONObject("booking").get("firstname"),resJsonPath.get("booking.firstname"));
        assertEquals(expBody.getJSONObject("booking").get("lastname"),resJsonPath.get("booking.lastname"));
        assertEquals(expBody.getJSONObject("booking").get("additionalneeds"),resJsonPath.get("booking.additionalneeds"));
        assertEquals(expBody.getJSONObject("booking").get("totalprice"),resJsonPath.get("booking.totalprice"));
        assertEquals(expBody.getJSONObject("booking").get("depositpaid"),resJsonPath.get("booking.depositpaid"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                    resJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                     resJsonPath.get("booking.bookingdates.checkout"));
    }
}
