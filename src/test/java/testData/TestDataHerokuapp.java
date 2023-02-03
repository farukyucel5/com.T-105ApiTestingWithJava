package testData;

import java.util.HashMap;

public class TestDataHerokuapp {

    /*
    {
       "bookingid":24,
       "booking":{
           "firstname":"Ali",
           "lastname":"Bak",
           "totalprice":500,
           "depositpaid":false,
           "bookingdates":{
               "checkin":"2021-06-01",
               "checkout":"2021-06-10"
           },
           "additionalneeds":"wi-fi"
           }
       }
     */

    public HashMap bookingDatesOlusturMap() {

        HashMap <String,Object> bookingdates = new HashMap<>();

        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        return bookingdates;
    }
    public HashMap bookingOlusturMap() {

        HashMap <String,Object> booking = new HashMap<>();

        booking.put("firstname","Ali");
        booking.put("lastname","Bak");
        booking.put("totalprice",500.0);
        booking.put("depositpaid",false);
        booking.put("bookingdates",bookingDatesOlusturMap());
        booking.put("additionalneeds","wi-fi");

        return booking;
    }

    public HashMap expBodyOlusturMap() {

        HashMap <String,Object> expBodyMap = new HashMap<>();

        expBodyMap.put("bookingid",24.0);
        expBodyMap.put("booking",bookingOlusturMap());

        return expBodyMap;
    }

}
