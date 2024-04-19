package com.ftc.flightcontrol.serializer;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.Clase;

public class ClaseSerializer extends JsonSerializer<Clase>  {

    @Override
    public void serialize(Clase value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("id");
        jsonGenerator.writeObject(value.getId());
        jsonGenerator.writeFieldName("descripcion");
        jsonGenerator.writeObject(value.getDescripcion());
        jsonGenerator.writeEndObject();
    }
}
