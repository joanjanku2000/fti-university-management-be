package al.edu.fti.universitymanagement.uniman.core.util;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomZonedDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    public CustomZonedDateTimeDeserializer() {
        this(null);
    }

    public CustomZonedDateTimeDeserializer(Class<?> c) {
        super(c);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return LocalDateTime.parse(jp.getText());
    }
}

