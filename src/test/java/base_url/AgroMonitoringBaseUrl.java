package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AgroMonitoringBaseUrl {
    //base url baska bir sinifta olustururum ve ihtiyacim oldugunda gider kullanirim


    // RequestSpercification data tipinde bir obje olustur
    protected RequestSpecification spec;

    //Eger methodun uzerinde @Before anotation kullanirsaniz, bu method her bir test methodundan önce calisir
    //
    @Before
    public void setUp(){
        spec= new RequestSpecBuilder().setBaseUri("http://api.agromonitoring.com").build();

    }
}
