package class01_get_http_request_method;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*         test case olusturma
   Given
       https://restful-booker.herokuapp.com/booking/3
   When
       Kullanici GET Request'i Url'e (APi) gonderir
       User send a GET Request to the url (API)
   Then
        HTTP Statu Kodu 200 olmali
       HTTP Status Code should be 200
   And
       Content Type'i JSON olmali
       Content Type should be JSON
   And
       Statu Line(duzeyi) HTTP/1.1 200 OK olmali
       Status Line should be HTTP/1.1 200 OK
*/
@Test
    public void get01(){
    //1. adim set ve url
    String url= "https://restful-booker.herokuapp.com/booking/4";

    //2. adim: Beklenen data (expected data) set et

    //3. adim: Get request gonderilir ve Get Response alinir.

    Response response=given().when().get(url);
    response.prettyPrint();

    //4. adim: assertion yap
    response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

    System.out.println("Status Code: "+response.getStatusCode());
    System.out.println("Content-Type: "+response.getContentType());
    System.out.println("Status Line: "+response.getStatusLine());
    System.out.println("Headers: "+response.getHeaders());
    System.out.println("Via: "+response.getHeader("Via"));

}

}
