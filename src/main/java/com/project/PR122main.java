package com.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.project.excepcions.IOFitxerExcepcio;
import com.project.objectes.PR122persona;

public class PR122main {
    private static String filePath = System.getProperty("user.dir") + "/data/PR122persones.dat";

    public static void main(String[] args) {
        List<PR122persona> persones = new ArrayList<>();
        persones.add(new PR122persona("Maria", "López", 36));
        persones.add(new PR122persona("Gustavo", "Ponts", 63));
        persones.add(new PR122persona("Irene", "Sales", 54));

        try {
            serialitzarPersones(persones);
            List<PR122persona> deserialitzades = deserialitzarPersones();
            deserialitzades.forEach(System.out::println);  // Mostra la informació per pantalla
        } catch (IOFitxerExcepcio e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Mètode per serialitzar la llista de persones
    public static void serialitzarPersones(List<PR122persona> persones) throws IOFitxerExcepcio {
        // *************** CODI PRÀCTICA **********************/
        String camiFitxer = getFilePath();

        try (FileOutputStream fos = new FileOutputStream(camiFitxer);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(persones);
            System.out.println("Objecte serialitzat correctament a " + camiFitxer);

        } catch (FileNotFoundException e) {
            throw new IOFitxerExcepcio("Fitxer no trobat: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new IOFitxerExcepcio("Error entrada/sortida: " + e.getMessage(), e);
        }
    }

    // Mètode per deserialitzar la llista de persones
    @SuppressWarnings("unchecked")
    public static List<PR122persona> deserialitzarPersones() throws IOFitxerExcepcio {
        // *************** CODI PRÀCTICA **********************/
        String camiFitxer = getFilePath();  
        
        try (FileInputStream fis = new FileInputStream(camiFitxer);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<PR122persona>) ois.readObject();

        } catch (FileNotFoundException e) {
            throw new IOFitxerExcepcio("Fitxer no trobat: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new IOFitxerExcepcio("Error entrada/sortida: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new IOFitxerExcepcio("No s'ha trobat la classe: " + e.getMessage(), e);
        }
    }


    // Getter i Setter per a filePath (opcional)
    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }
}
