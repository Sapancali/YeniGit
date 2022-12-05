package class02_post_http_request_method;

import Utils.JsonUtil;
import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import class06_pojos.HerOkuAppPostResponseBodyPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostGetVeObjectMapperVePojo01 extends HerOkuAppBaseUrl {
    /*
   Given
       https://restful-booker.herokuapp.com/booking
       {
       "firstname": "James",
       "lastname": "Can",
       "totalprice": 1245,
       "depositpaid": true,
       "bookingdates": {
               "checkin": "2022-09-09",
               "checkout": "2022-09-21"
           },
        "additionalneeds": "Orange juice"
          }
   When
        Url'e POST Request gonderdim
   And
        Url'e Get Request gonderdim
   Then
       Status code is 200
   And
       GET response body asagidaki gibi olmali
               {
                       "bookingid": 40208,
                       "booking": {
                       "firstname": "James",
                       "lastname": "Can",
                       "totalprice": 1245,
                       "depositpaid": true,
                       "bookingdates": {
                               "checkin": "2022-09-09",
                               "checkout": "2020-09-21"
                           },
                       "additionalneeds": "Orange juice"
                       }
                  }
 */
    @Test
    public void test01(){
        //1.yol url i set et
        spec.pathParam("first","booking");

        //2. adım beklenen expected datayı / requesti set et
        BookingDatesPojo bookingDate=new BookingDatesPojo("2022-09-09","2020-09-21");
        BookingPojo expectedData=new BookingPojo("James","Can",1245,true,bookingDate,"Orange juice");

        //3. adım Post request gonder response al
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        HerOkuAppPostResponseBodyPojo postResponsePojo= JsonUtil.jsoniJavayaCevir(response.asString(), HerOkuAppPostResponseBodyPojo.class);
        System.out.println(postResponsePojo);
        Integer bookingId= postResponsePojo.getBookingid();

        // bookingid kullanarak Get request gondereceğiz

        //1. adım url i set et
        spec.pathParams("first","booking","second",bookingId);

        //2. adım expected datayı set et
        //Expected datayı set etmeye gerek yok, çünkü Post request ile gonderdiğim expected data ile aynıdır

        //3. adım Get request gonder response al
        Response response1= given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response1.prettyPrint();

        //ObjectMapper Kullanarak Get request body i java object e donüştür
        BookingPojo getResposePojo= JsonUtil.jsoniJavayaCevir(response1.asString(),BookingPojo.class);
        System.out.println(getResposePojo);

        //4. adım assertion yap
        response1.then().statusCode(200);
        assertEquals(postResponsePojo.getBooking().getFirstname(),getResposePojo.getFirstname());
        assertEquals(postResponsePojo.getBooking().getLastname(),getResposePojo.getLastname());

        assertEquals(postResponsePojo.getBooking().getBookingdates().getCheckout(),getResposePojo.getBookingdates().getCheckout());

        //veya
        assertEquals(expectedData.getTotalprice(),getResposePojo.getTotalprice());
        assertEquals(expectedData.getAdditionalneeds(),getResposePojo.getAdditionalneeds());
        assertEquals(expectedData.getBookingdates().getCheckin(),getResposePojo.getBookingdates().getCheckin());



    }
}
