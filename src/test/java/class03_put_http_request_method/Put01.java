package class03_put_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
  Given
      https://jsonplaceholder.typicode.com/todos/198

    {
      "userId": 21,
      "title": "Camasirlari yikadin mi",
      "completed": false
     }
  When
       URl'e PUT Request gonder
  Then
       Status code is 200
       And response body is like
       {
          "userId": 21,
          "title": "Camasirlari yikadin mi",
          "completed": false
         }
   */
    @Test
    public void put01() {
        //1. adım url set et
        spec.pathParams("first", "todos", "second", 198);

        //2. adım request body veya expected data set et
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataSetUpTumKey(21,"Camasirlari yikadin mi",false);

        //3. adım request gonder response al
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
        response.prettyPrint();
//4. adım assertion yap
        //1. yol
        response.then().assertThat().statusCode(200).body("completed",equalTo(requestBodyMap.get("completed")),
                "title",equalTo(requestBodyMap.get("title")),
                "userId",equalTo(requestBodyMap.get("userId")));

        //2. yol
        // Gson kullanarak = de-serialization yapıyoruz Json dan Java object
        Map<String,Object> gercekDataMap= response.as(HashMap.class);
        assertEquals(requestBodyMap.get("completed"),gercekDataMap.get("completed"));

  // Gson kullanarak Serialization yapmak Java object data == Json formatına donüştürüyoruz

        Map<String,Integer> yas= new HashMap<>();
        yas.put("Ali Can",15);
        yas.put("Veli Han",18);
        yas.put("Ayse Kan",21);
        yas.put("Tom Hanks",65);
System.out.println(yas);
        //yas == Json formatına donüstür
        //1. adım
        Gson gson= new Gson();
        //2. adım gson object formatını kullanarak Java object Json formatına donüstür
        String JavadanJsona= gson.toJson(yas);
        System.out.println(JavadanJsona);

    }
}
