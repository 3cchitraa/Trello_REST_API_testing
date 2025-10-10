package utils;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadManager
{
    public static String getPayload(String fileName)
    {
        try
        {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/payloads/"+ fileName)));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to load payload file : "+fileName, e);
        }
    }

    public static String getBoardName(String fileName)
    {
        String json = getPayload(fileName);
        JSONObject obj = new JSONObject(json);

        return obj.getString("name");
    }
}
