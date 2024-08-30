package actitimeautomation.imp1.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandling {
    Properties properties;
    public PropertyHandling() throws IOException {

        //access config.properties file

        //1. get the path of project
        String filePath = System.getProperty("user.dir")+ "/config.properties";

        //2. access the config.properties file
        FileInputStream inputStream= new FileInputStream(filePath);
        //3. create an object of Properties class
        properties = new Properties();

        //4. Load file into Properties class
        properties.load(inputStream);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
