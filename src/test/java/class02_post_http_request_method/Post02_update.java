package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02_update extends JsonPlaceHolderBaseUrl {
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
        JsonPlaceHolderTestData expectedData= new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap= expectedData.expectedDataSetUp();


        //3. adım request gonder ve respond al
        Response response= given().
                spec(spec).auth().basic("admin","1234").
                contentType(ContentType.JSON).
                body(expectedData).when().
                post("/{first}");
        response.prettyPrint();

        expectedDataMap.put("Status code",201);

        //4. adım assertion yap
        Map<String, Object> actualData= response.as(HashMap.class);
        System.out.println(actualData);

        response.then().statusCode(201);

        assertEquals(expectedDataMap.get("Status Code"),response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));


    }

}
