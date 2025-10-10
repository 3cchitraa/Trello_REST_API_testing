package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader
{
    private static Properties properties = new Properties();

        static {
            try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties"))
            {
                if(inputStream==null)  //Check if file doesn't exist
                {
                    throw new RuntimeException("config.properties not found in classpath");
                }
                properties.load(inputStream);   //load the file and read it

            } catch (IOException e) {   //any error while reading the file
                throw new RuntimeException("Failed to load or reading the config file. ",e);
            }
        }

        public static String get(String key)
        {
            return properties.getProperty(key);
        }






}
