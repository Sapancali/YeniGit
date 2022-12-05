package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
  /*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
            Url'e GET Request gonder
    Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
         {
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
         }
 */
    @Test
    public void get08(){
        //1. adim url set et
        spec.pathParams("first","todos", "second",2);

        //2. adim expected datayı set et
        Map<String, Object> expectedData= new HashMap<>();
       expectedData.put("userId",1);
       expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("Status Code",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        System.out.println(expectedData);

        //3. adım request gonder response al
       Response response= given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       //Gson kullanarak API dan gelen Json datayı object formatına ceviriyoruz
        Map<String,Objects> actualData =response.as(HashMap.class);
        System.out.println(actualData);

        //4. adım assertion yap
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("Status Code"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));

    }

}
