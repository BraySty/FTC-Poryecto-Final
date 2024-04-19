package com.ftc.flightcontrol.utils;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.Clase;

public class VueloClaseSerializer extends JsonSerializer<Clase>  {

    @Override
    public void serialize(Clase value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("descripcion");
        jsonGenerator.writeObject(value.getDescripcion());
        jsonGenerator.writeEndObject();
    }
}
