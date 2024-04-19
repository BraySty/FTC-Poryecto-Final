package com.ftc.flightcontrol.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.Role;

public class UsuarioRolSerializer extends JsonSerializer<Role>  {

    @Override
    public void serialize(Role value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("descripcion");
        jsonGenerator.writeObject(value.getDescripcion());
        jsonGenerator.writeEndObject();
    }

}
