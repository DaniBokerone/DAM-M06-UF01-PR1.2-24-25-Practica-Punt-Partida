package com.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.project.excepcions.IOFitxerExcepcio;
import com.project.objectes.PR121hashmap;

public class PR121mainLlegeix {
    private static String filePath = System.getProperty("user.dir") + "/data/PR121HashMapData.ser";

    public static void main(String[] args) {
        try {
            PR121hashmap hashMap = deserialitzarHashMap();
            hashMap.getPersones().forEach((nom, edat) -> System.out.println(nom + ": " + edat + " anys"));
        } catch (IOFitxerExcepcio e) {
            System.err.println("Error al llegir l'arxiu: " + e.getMessage());
        }
    }

    public static PR121hashmap deserialitzarHashMap() throws IOFitxerExcepcio {
        // *************** CODI PRÀCTICA **********************/
        String camiFitxer = getFilePath();  
        
        try (FileInputStream fis = new FileInputStream(camiFitxer);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (PR121hashmap) ois.readObject();

        } catch (FileNotFoundException e) {
            throw new IOFitxerExcepcio("Error en deserialitzar l'objecte HashMap - Fitxer no trobat: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new IOFitxerExcepcio("Error en deserialitzar l'objecte HashMap - Error entrada/sortida: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new IOFitxerExcepcio("Error en deserialitzar l'objecte HashMap - No s'ha trobat la classe: " + e.getMessage(), e);
        }
    }

    // Getter
    public static String getFilePath() {
        return filePath;
    }

    // Setter
    public static void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }    
}