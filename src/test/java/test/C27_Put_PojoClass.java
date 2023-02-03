package test;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import pojos.POJOJsonPlaceHolder;

import static io.restassured.RestAssured.given;

public class C27_Put_PojoClass extends JsonPlaceHolderBaseUrl {
    /*
    C27_Put_PojoClass
 https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
 body’e sahip bir PUT request yolladigimizda donen response’in
 response body’sinin asagida verilen ile ayni oldugunu test ediniz

     Request Body

    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }

    Expected Body

    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
     */
    @Test
    public void put01(){

        // 1 - URL ve Body hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        POJOJsonPlaceHolder reqBody = new POJOJsonPlaceHolder("Ahmet","Merhaba",70,10);

        System.out.println("reqBody = " + reqBody);

        // 2 - Expected Data hazirla

        POJOJsonPlaceHolder expData = new POJOJsonPlaceHolder("Ahmet","Merhaba",70,10);

        System.out.println("expData = " + expData);
        
        // 3 - Response'i kaydet

        Response response = given().
                                    spec(specJsonPlace).
                                    contentType(ContentType.JSON).
                            when().
                                    body(reqBody).
                                    put("/{pp1}/{pp2}");

        response.prettyPrint();
        
        // 4 - Assertion

        POJOJsonPlaceHolder respPojo = response.as(POJOJsonPlaceHolder.class);

        Assert.assertEquals(expData.getBody(),respPojo.getBody());
        Assert.assertEquals(expData.getId(),respPojo.getId());
        Assert.assertEquals(expData.getTitle(),respPojo.getTitle());
        Assert.assertEquals(expData.getUserId(),respPojo.getUserId());
    }
}
