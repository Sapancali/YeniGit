package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/11
        When
            Url'e GET Request gonder
        Then
            Response body asagidaki gibi olmali;
            {
                "firstname": "Mahek",
                "lastname": "Sayyed",
                "totalprice": 3000,
                "depositpaid": true,
                "bookingdates":
                        {
                        "checkin": "2022-12-03",
                        "checkout": "2022-12-04"
                         }
                "additionalneeds": "Breakfast"
            }
     */
@Test
    public void get09(){
    //url set et
    spec.pathParams("first","booking","second",11);

    //2. adım expected datayı set et

    Map<String,String> expectedBokingDates=new HashMap<>();
    expectedBokingDates.put("checkin","2022-12-03");
    expectedBokingDates.put("checkout","2022-12-04");

    Map<String,Object> expectedData=new HashMap<>();
    expectedData.put("firstname","Mahek");
    expectedData.put("lastname","Sayyed");
    expectedData.put("totalprice",3000);
    expectedData.put("depositpaid",true);
    expectedData.put("bookingdates",expectedBokingDates);
    expectedData.put("additionalneeds","Breakfast");

    System.out.println(expectedData);

    // 3. adım: request gonder , respond al
    Response response= given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    Map<String, Object> actualData= response.as(HashMap.class);
    System.out.println(actualData);
    System.out.println(((Map)actualData.get("bookingdates")).get("checkin"));

    //4. adım : assertion yap

    assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
    assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
    assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
    assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
    assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
    assertEquals(expectedBokingDates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
    assertEquals(expectedBokingDates.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));
}

}
