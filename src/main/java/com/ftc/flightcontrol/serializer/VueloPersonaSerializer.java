package com.ftc.flightcontrol.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.VueloPersona;

public class VueloPersonaSerializer extends JsonSerializer<VueloPersona> {

    @Override
    public void serialize(VueloPersona value, JsonGenerator jsonGenerator, SerializerProvider serializers)
            throws IOException {
        jsonGenerator.writeStartObject();
        if (value != null) {
            if (value.getUsuario() != null) {
                jsonGenerator.writeFieldName("dni");
                jsonGenerator.writeObject(value.getUsuario().getDni());
                jsonGenerator.writeFieldName("nombre");
                jsonGenerator.writeObject(value.getUsuario().getNombre());
                jsonGenerator.writeFieldName("apellido");
                jsonGenerator.writeObject(value.getUsuario().getApellido());
                jsonGenerator.writeFieldName("correo");
                jsonGenerator.writeObject(value.getUsuario().getCorreo());
            } else {
                jsonGenerator.writeNull();
            }
            jsonGenerator.writeEndObject();
        }
    }

}
