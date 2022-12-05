package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post01 extends HerOkuAppBaseUrl {
    /*
Given
 https://restful-booker.herokuapp.com/booking/115178
    {
    "firstname": "Mustafa",
    "lastname": "Yilmaz",
    "totalprice": 11111,
    "depositpaid": true,
    "bookingdates": {
          "checkin": "2022-11-11",
          "checkout": "2022-11-12"
    }
}
When
     URL'e POST Request gonder
Then
    Status code is 200
    And response body asagidaki olmali
            {
          "bookingid": 11,
          "booking": {
                {
                "firstname": "Mustafa",
                "lastname": "Yilmaz",
                "totalprice": 11111,
                "depositpaid": true,
                 "bookingdates": {
                            "checkin": "2022-11-11",
                            "checkout": "2022-11-12"
                     }
                }
 */
    @Test
    public void post01(){
    //1. adım url i set et
    spec.pathParams("first","booking");

    //2. adım  expected datayı set et
        Map<String,String> expectedBokingDates=new HashMap<>();
        expectedBokingDates.put("checkin","2022-11-11");
        expectedBokingDates.put("checkout","2022-11-12");

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","Mustafa");
        expectedData.put("lastname","Yilmaz");
        expectedData.put("totalprice",11111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",expectedBokingDates);
       // expectedData.put("additionalneeds","Breakfast");

        System.out.println(expectedData);

        //3. adım Post request gonder response al
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //4. adım assertion yap
        response.then().assertThat().statusCode(200);
        Map<String, Object> actualData= response.as(HashMap.class);
        System.out.println(actualData);
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));

        assertEquals(expectedBokingDates.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedBokingDates.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

    }


}
