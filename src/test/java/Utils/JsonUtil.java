package Utils;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper mapper;

    static{
        mapper =new ObjectMapper();
    }
    //1. method Json datayı Java Object e cevirmek icin kullanılır. de-serialization

    // generic method
    public static <T> T jsoniJavayaCevir(String json,Class<T> cls){
        T javaSonuc=null;
        try {
            javaSonuc= mapper.readValue(json,cls);
        } catch (IOException e) {
            System.out.println("Json formatini Java Object formatina donusturemedim"+ e.getMessage());

        }
        return javaSonuc;
    }

    //2.method Java Object Json data ya çevirmek için kullanılır== serialization
    public static String javayiJsonaCevir(Object obje){
        String jsonSonuc=null;

        try {
            jsonSonuc= mapper.writeValueAsString(obje);
        } catch (IOException e) {
            System.out.println(" Java Objecti Json formatina donusturemedim"+ e.getMessage());
        }
        return jsonSonuc;
    }

}
