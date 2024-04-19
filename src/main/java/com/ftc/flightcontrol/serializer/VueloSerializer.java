package com.ftc.flightcontrol.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ftc.flightcontrol.entitys.Vuelo;
import com.ftc.flightcontrol.entitys.VueloPersona;

public class VueloSerializer extends JsonSerializer<Vuelo> {

    @Override
    public void serialize(Vuelo value, JsonGenerator jsonGenerator, SerializerProvider serializers)
            throws IOException {
        jsonGenerator.writeStartObject();
        if (value != null) {
            jsonGenerator.writeFieldName("id");
            jsonGenerator.writeObject(value.getId());
            jsonGenerator.writeFieldName("salida");
            jsonGenerator.writeObject(value.getSalida());
            jsonGenerator.writeFieldName("horaSalida");
            jsonGenerator.writeObject(value.getHoraSalida());
            jsonGenerator.writeFieldName("llegada");
            jsonGenerator.writeObject(value.getSalida());
            jsonGenerator.writeFieldName("horaLlegada");
            jsonGenerator.writeObject(value.getHoraSalida());
            if (value.getClase() != null) {
                jsonGenerator.writeFieldName("clase");
                jsonGenerator.writeString(value.getClase().getDescripcion());
            } else {
                jsonGenerator.writeNullField("clase");
            }
            if (value.getAvion() != null) {
                jsonGenerator.writeFieldName("avion");
                jsonGenerator.writeString(value.getAvion().getMatricula());
            } else {
                jsonGenerator.writeNullField("avion");
            }
            if (!value.getVueloPersona().isEmpty()) {
                jsonGenerator.writeFieldName("usuario");
                jsonGenerator.writeStartArray();
                for (VueloPersona element : value.getVueloPersona()) {
                    jsonGenerator.writeObject(element.getUsuario());
                }
                jsonGenerator.writeEndArray();
            } else {
                jsonGenerator.writeNullField("usuario");
            }
            // Otros campos de Avion que desees serializar
        } else {
            // Si el valor es nulo, escribe nulo para todos los campos
            jsonGenerator.writeNull();
        }
        jsonGenerator.writeEndObject();
    }
}