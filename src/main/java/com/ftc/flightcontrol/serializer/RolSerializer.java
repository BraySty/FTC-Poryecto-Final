package com.ftc.flightcontrol.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.Role;

public class RolSerializer extends JsonSerializer<Role>  {

    @Override
    public void serialize(Role value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (value != null) {
            jsonGenerator.writeFieldName("descripcion");
            jsonGenerator.writeObject(value.getDescripcion());
            // Otros campos de Avion que desees serializar
        } else {
            // Si el valor es nulo, escribe nulo para todos los campos
            jsonGenerator.writeNull();
        }
        jsonGenerator.writeEndObject();
    }

}
