package class02_post_http_request_method;

import Utils.JsonUtil;
import base_url.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostVeObjectMapperVePojo extends JsonPlaceHolderBaseUrl {
 /*
    Given
        https://jsonplaceholder.typicode.com/todos
        {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false
        }
    When
        I send POST Request to the Url
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
    public void test01(){
        //1. adım url i set et
        spec.pathParam("first","todos");

        //2. adım
        JsonPlaceHolderTestData expected=new JsonPlaceHolderTestData();
        String expectedData= expected.beklenenDataStringFormatinda(55,"Tidy your room",false);

        JsonPlaceHolderPojo expectedDataPojo= JsonUtil.jsoniJavayaCevir(expectedData, JsonPlaceHolderPojo.class);
        System.out.println(expectedDataPojo);

        //3. adım request gonder response al
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        JsonPlaceHolderPojo actualData= JsonUtil.jsoniJavayaCevir(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println(actualData);

        //4.adım assertion yap
        assertEquals(201,response.getStatusCode());
        assertEquals(expectedDataPojo.getUserId(),actualData.getUserId());
        assertEquals(expectedDataPojo.getTitle(),actualData.getTitle());
        assertEquals(expectedDataPojo.getCompleted(),actualData.getCompleted());



    }
}
