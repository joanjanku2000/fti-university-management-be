package al.edu.fti.universitymanagement.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {
    private static ObjectMapper objectMapper;
    private static ObjectWriter objectWriter;
    private static ObjectReader objectReader;
    static {
        objectMapper = new ObjectMapper();
        objectWriter = objectMapper.writer();
        objectReader = objectMapper.reader();
    }

    public static String toJson(Object o) throws JsonProcessingException {
        return objectWriter.writeValueAsString(o);
    }

    public static Object parseJson(String json) throws JsonProcessingException {
        return objectReader.readValue(json);
    }
}
