package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /*
    Given
    https://jsonplaceholder.typicode.com/todos
    When
    URl'e GET Request gonder
    Then
    Status code  200 olmali
             1) 190'dan buyuk Id'leri yazdir
               190'dan buyuk 10 Id'nin oldugunu Assert et
             2) userIds 5'ten kucuk olanlari yazdir
                UserId'si 5'ten kucuk en buyuk degerin 4 oldugunu assert et
             3) id'si 5'ten kucuk olan butun title'lari yazdir
            "delectus aut autem"un 5'ten kucuk title'lardan bir tanesi oldugunu assert et.
 */
    @Test
    public void get07 (){
        //1. adim: url set et
        spec.pathParam("first","todos");

        //2. adim: beklenen datayı test et


        //3. adim: get repuest gonder ve get response al
        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4. adim: assertion yap
        response.then().assertThat().statusCode(200);
        //190'dan buyuk Id'leri yazdir
        JsonPath json=response.jsonPath();

        List<Integer> idList= json.getList("findAll{it.id>190}.id"); // groovy language
        System.out.println(idList);

        //190'dan buyuk 10 Id'nin oldugunu Assert et
        assertEquals("Id listesi beklenen ile uyusmuyor",10,idList.size());

        //userIds 5'ten kucuk olanlari yazdir
        List<Integer> useridList= json.getList("findAll{it.userId<5}.userId");
        System.out.println(useridList);
        
        //UserId'si 5'ten kucuk en buyuk degerin 4 oldugunu assert et

        //   HATA VAR


        //id'si 5'ten kucuk olan butun title'lari yazdir
        List<String> titleList= json.getList("findAll{it.userId<5}.title");
        System.out.println(titleList);

        //"delectus aut autem"un 5'ten kucuk title'lardan bir tanesi oldugunu assert et.
        //1.yol
        assertTrue(titleList.contains("delectus aut autem"));

        //2. yol
        assertTrue(titleList.stream().anyMatch(t->t.equals("delectus aut autem")));

    }


}
