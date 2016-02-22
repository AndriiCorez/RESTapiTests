package countryTests;

import base.BaseTest;
import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;


public class CountryTests extends BaseTest {

    @Test
    public void capitalCorrectness(){
        String key = "capital";
        String expectedValue = "Kiev";

        expect().statusCode(200)
                .body(key, equalTo(Arrays.asList(expectedValue)))
                .when()
                .get(url + countryTag);
    }

    @Test
    public void populationCorrectness(){
        String key = "population";
        int expectedValue = 42836922;

        Assert.assertEquals(getKeyValueInt(countryTag, 0, key), expectedValue);
    }

    @Test
    public void giniCorrectness(){
        String key = "gini";
        double expectedValue = 26.4;

        Assert.assertEquals(getKeyValueDouble(countryTag, 0, key), expectedValue);
    }

    @Test
    public void bordersCorrectness(){
        String key = "borders";
        ArrayList<String> expectedValues = new ArrayList<String>(){
            {add("BLR");add("HUN");add("MDA");add("POL");add("ROU");add("RUS");add("SVK");}};

        Assert.assertEquals(getKeyValueArrayOfStrings(countryTag, 0, key),expectedValues);
    }

}
