package com.ftc.flightcontrol.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.Avion;

public class VueloAvionSerializer extends JsonSerializer<Avion> {

    @Override
    public void serialize(Avion value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (value != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("matricula");
            jsonGenerator.writeObject(value.getMatricula());
            jsonGenerator.writeFieldName("capacidad");
            jsonGenerator.writeNumber(value.getCapacidad());
            jsonGenerator.writeFieldName("carga");
            jsonGenerator.writeNumber(value.getCarga());
            jsonGenerator.writeEndObject();
        }
    }

}
