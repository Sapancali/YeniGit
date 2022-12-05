package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class
GetVePojo01 extends HerOkuAppBaseUrl {
/*
Given
        https://restful-booker.herokuapp.com/booking/2
When
   Url'e GET Request gonder
Then
    Status code is 200
And response body is like
        {
            "firstname": "James",
            "lastname": "Brown",
            "totalprice": 2400,
            "depositpaid": true,
            "bookingdates": {
                               "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                }
             "additionalneeds": "Breakfast"
          }
 */
    @Test
    public void getVePojo01(){
    //1. adım url i set et
    spec.pathParams("ilk","booking","ikinci",2000);

    //2. adım expected datayı set et

        BookingDatesPojo bookingDates= new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData= new BookingPojo("Josh","Allen",111,true,bookingDates,"superb owls");
        System.out.println(expectedData);

        //3. adım request gonder respond al
        Response response= given().spec(spec).when().get("/{ilk}/{ikinci}");
        response.prettyPrint();

        //4. adım assertion yap
        BookingPojo actualData= response.as(BookingPojo.class);
        System.out.println(actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals("isimler eslesmiyor",expectedData.getFirstname(),actualData.getFirstname());
        assertEquals("soyisimler eslesmiyor", expectedData.getLastname(),actualData.getLastname());
        assertEquals("toplam ücret eslesmiyor",expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());


    }
}
