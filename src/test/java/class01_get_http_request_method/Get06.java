package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

    @Test
    public void get06(){

//1. adim: url set et
        spec.pathParams("first","booking","second",560);

//2. adim: beklenen datayı test et

//3. adim: get repuest gonder ve get response al
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
//4. adim: assertion yap
    //1. yol
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("Sally")).
                body("lastname",equalTo("Brown")).
                body("totalprice",equalTo(111)).
                body("depositpaid",equalTo(true)).
                body("bookingdates.checkin",equalTo("2013-02-23")).
                body("bookingdates.checkout",equalTo("2014-10-23")).
                body("additionalneeds",equalTo("Breakfast"));

    //2.yol :  JsonPath kullanarak assertion yaparız
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        JsonPath json = response.jsonPath();
        assertEquals("isimler eslesmiyor","Sally",json.getString("firstname"));
        assertEquals("soyisimler eslesmiyor","Brown",json.getString("lastname"));
        assertEquals("total price eslesmiyor",111,json.getInt("totalprice"));
//HATA        //assertEquals("depositpaid eslesmiyor", true, json.get("depositpaid"));
        assertEquals("checkindate eslesmiyor","2013-02-23",json.getString("bookingdates.checkin"));

        //3. yol  SoftAssertion
            //i- SoftAssert objesini olusturma
        SoftAssert softAssert=new SoftAssert();

            //ii- SoftAssert objesini kullanarak Assertion yapmak
        softAssert.assertEquals(json.getString("firstname"),"Sally","isimler eslesmiyor");
        softAssert.assertEquals(json.getString("lastname"),"Brown","soyisimler eslesmiyor");
        softAssert.assertEquals(json.getInt("totalprice"),111,"total price eslesmiyor");
//HATA        softAssert.assertEquals(json.get("depositpaid"),true,"depositpaid eslesmiyor");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2013-02-23","checkin eslesmiyor");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2014-10-23","checkout eslesmiyor");
        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast","additionalneeds eslesmiyor");

            //iii- MUTLAKA EN SONDA assertAll() yapılmalı. Eger assertAll() kullanılmazsa test geçti gorunur fakat gerçekte geçmemiş olabilir
        softAssert.assertAll();

    }


}
