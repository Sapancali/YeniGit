package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {
    /*
   Given
            https://jsonplaceholder.typicode.com/todos
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
              }
    When
           Url'e POST Request gonder
    Then
        Status code is 201
    And
        response body is like {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
         }
 */
@Test
public void post02(){
//1. adım url i set et
    spec.pathParam("first","todos");

    //2. adım expected datayı set et
    Map<String,Object> expectedData= new HashMap<>();
    expectedData.put("userId",55);
    expectedData.put("title","Tidy your room");
    expectedData.put("completed", false);
    System.out.println(expectedData);

    //3. adım request gonder ve respond al
    Response response= given().
                                spec(spec).
                                contentType(ContentType.JSON).
                                body(expectedData).when().
                                post("/{first}");
    response.prettyPrint();

    expectedData.put("Status code",201);

    //4. adım assertion yap
    Map<String, Object> actualData= response.as(HashMap.class);
    System.out.println(actualData);

    response.then().statusCode(201);

   // assertEquals(expectedData.get("Status Code"),response.getStatusCode());
    assertEquals(expectedData.get("userId"),actualData.get("userId"));
    assertEquals(expectedData.get("title"),actualData.get("title"));
    assertEquals(expectedData.get("completed"),actualData.get("completed"));


}
}
