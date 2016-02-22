package countryTests;

import static com.jayway.restassured.RestAssured.expect;

import base.BaseTest;
import org.testng.annotations.Test;


public class StatusCodeTests extends BaseTest {

    @Test
    public void OK(){
        expect().statusCode(200).
                get(url + countryTag);
    }

    @Test
    public  void notFound(){
        expect().statusCode(404).
                get(url + "foo");
    }
}
