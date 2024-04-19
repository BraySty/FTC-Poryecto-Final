package com.ftc.flightcontrol.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.usuarios.Usuario;

public class UsuarioSerializer extends JsonSerializer<Usuario>  {

    @Override
    public void serialize(Usuario value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        if (value != null) {
            jsonGenerator.writeFieldName("dni");
            jsonGenerator.writeObject(value.getDni());
            jsonGenerator.writeFieldName("nombre");
            jsonGenerator.writeObject(value.getNombre());
            jsonGenerator.writeFieldName("apellido");
            jsonGenerator.writeObject(value.getApellido());
            jsonGenerator.writeFieldName("correo");
            jsonGenerator.writeObject(value.getCorreo());
            if (value.getRole() != null) {
                jsonGenerator.writeFieldName("role");
                jsonGenerator.writeObject(value.getRole().getDescripcion());
            } else {
                jsonGenerator.writeNullField("role");
            }
            // Otros campos de Avion que desees serializar
        } else {
            // Si el valor es nulo, escribe nulo para todos los campos
            jsonGenerator.writeNull();
        }
        jsonGenerator.writeEndObject();
    }

}