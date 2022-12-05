package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {

    @Test
    public void get04(){
        //1. adim: url set et
        spec.pathParams("birinci","todos");
        //2. adim: beklenen datayı test et

        //3. adim: get repuest gonder ve get response al
        Response response=given().spec(spec).accept(ContentType.JSON).when().get("/{birinci}");
        response.prettyPrint();
        //4. adim: assertion yap
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200)).
                body("title",hasItem("quis eius est sint explicabo")).
                body("userId",hasItems(2,7,9));

    }

}
//1. adim: url set et

//2. adim: beklenen datayı test et

//3. adim: get repuest gonder ve get response al

//4. adim: assertion yap