package com.ftc.flightcontrol;

import java.io.*;

import com.ftc.flightcontrol.entitys.usuarios.Usuario;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Usuario usuario = new Usuario("DNI","Nombre","Apellido","Correo","Password", null, null);

        FileOutputStream outputStream = new FileOutputStream("save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // Save the game to a file
        objectOutputStream.writeObject(usuario.toString());

        // Close the stream and release resources
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("save.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Usuario savedGame = (Usuario) objectInputStream.readObject();

        if (Serializable.class.isAssignableFrom(Usuario.class)) {
            System.out.println("MiClase es serializable.");
        } else {
            System.out.println("MiClase no es serializable.");
        }

        System.out.println(savedGame.toString());
    }

}
