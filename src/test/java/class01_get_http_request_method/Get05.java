package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    @Test
    public void get05(){
        //1. adim: url set et
        spec.pathParam("first","booking").queryParams("firstname","Fabio","lastname","Rincon");

        //2. adim: beklenen datayı test et


        //3. adim: get repuest gonder ve get response al
        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4. adim: assertion yap
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("bookingid",hasItem(1585));

        response.then().assertThat().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));

    }
}
