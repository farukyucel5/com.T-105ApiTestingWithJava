package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_ResponseBilgileriAssertion {

    /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
        gonderdigimizde donen Response’un,

        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK

        oldugunu test edin.
     */

    @Test
    public void get01(){

        // 1 - Gerekli olan URL ve Body hazirla

        String url = "https://restful-booker.herokuapp.com/booking/8989";

        // 2 - Soruda isteniyorsa Expected Data hazirla

        // 3 - Donen Response'i kaydet

        Response response = given().when().get(url);

        response.prettyPrint();

        // 4 - Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                header("Server","Cowboy").
                statusLine("HTTP/1.1 200 OK");
    }
}
