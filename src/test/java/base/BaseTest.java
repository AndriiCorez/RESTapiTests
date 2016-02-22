package base;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import static com.jayway.restassured.RestAssured.get;

public class BaseTest {
    protected String url;
    protected String countryTag;

    public BaseTest(){
        if(url == null){
            try{
                Initialize();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void Initialize() throws FileNotFoundException {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("rest-test.properties");

        try{
            properties.load(stream);
        }catch (IOException e){
            throw new FileNotFoundException();
        }

        url = properties.getProperty("rest-test.url");
        countryTag = properties.getProperty("rest-test.country");

    }

    protected String getKeyValueString(String tag, int objectID, String keyName){
        return new JSONArray(get(url + tag).asString()).getJSONObject(objectID).getString(keyName);
    }

    protected int getKeyValueInt(String tag, int objectID, String keyName){
        return new JSONArray(get(url + tag).asString()).getJSONObject(objectID).getInt(keyName);
    }

    protected double getKeyValueDouble(String tag, int objectID, String keyName){
        return new JSONArray(get(url + tag).asString()).getJSONObject(objectID).getDouble(keyName);
    }

    protected ArrayList getKeyValueArrayOfStrings(String tag, int objectID, String keyName){
        JSONArray jArray = new JSONArray(get(url + tag).asString()).getJSONObject(objectID).getJSONArray(keyName);
        ArrayList<String> listData = new ArrayList<String>();
        if(jArray != null){
            for(int i = 0; i < jArray.length(); i++)
                listData.add(jArray.get(i).toString());
        }else {
            return null;
        }
        return listData;
    }




}
