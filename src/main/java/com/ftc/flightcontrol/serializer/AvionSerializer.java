package com.ftc.flightcontrol.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.Avion;

public class AvionSerializer extends JsonSerializer<Avion> {

    @Override
    public void serialize(Avion value, JsonGenerator jsonGenerator, SerializerProvider serializers)
            throws IOException {
        jsonGenerator.writeStartObject();
        if (value != null) {
            jsonGenerator.writeFieldName("matricula");
            jsonGenerator.writeObject(value.getMatricula());
            jsonGenerator.writeFieldName("capacidad");
            jsonGenerator.writeNumber(value.getCapacidad());
            jsonGenerator.writeFieldName("carga");
            jsonGenerator.writeNumber(value.getCarga());
            // Otros campos de Avion que desees serializar
        } else {
            // Si el valor es nulo, escribe nulo para todos los campos
            jsonGenerator.writeNull();
        }
        jsonGenerator.writeEndObject();
    }
}
