package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
    protected RequestSpecification spec;

    // eğer methodun üzerinde @before annotation eklersek, bu methodu her bir test methodundan önce calıştır
    @Before
    public void setup(){
    spec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

}
