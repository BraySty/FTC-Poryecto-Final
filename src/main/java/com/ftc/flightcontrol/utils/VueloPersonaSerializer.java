package com.ftc.flightcontrol.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.VueloPersona;

public class VueloPersonaSerializer extends JsonSerializer<VueloPersona> {

    @Override
    public void serialize(VueloPersona value, JsonGenerator jsonGenerator, SerializerProvider serializers)
            throws IOException {
        if (value != null) {
            if (value.getUsuario() != null) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName("dni");
                jsonGenerator.writeObject(value.getUsuario().getDni());
                jsonGenerator.writeFieldName("nombre");
                jsonGenerator.writeObject(value.getUsuario().getNombre());
                jsonGenerator.writeFieldName("apellido");
                jsonGenerator.writeObject(value.getUsuario().getApellido());
                jsonGenerator.writeFieldName("correo");
                jsonGenerator.writeObject(value.getUsuario().getCorreo());
                jsonGenerator.writeEndObject();
            } else {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName("usuario");
                jsonGenerator.writeNull();
                jsonGenerator.writeEndObject();
            }
        }
    }

}
