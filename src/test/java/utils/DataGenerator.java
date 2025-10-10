package utils;

public class DataGenerator
{
    public static String generateUniqueName(String prefix)
    {
        return prefix + "_" + System.currentTimeMillis();
    }

}
