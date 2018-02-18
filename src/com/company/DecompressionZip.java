package com.company;

import java.io.*;
import java.lang.Runtime;

/**
 * Gere la décompression du fichier Soumission.zip
 */
public class DecompressionZip {
    public  String PathProjet;

    /**
     * Constructeur de la classe DecompressionZip
     * @param nom
     */
    public DecompressionZip(String nom){
        PathProjet = nom;
    }

    /**
     * Verifie si le fichier est au format ".zip"
     */
    public boolean testFormat(){
        return PathProjet.contains(".zip");
    }

    /**
     * Decompresse le fichier zip
     */
    public void dezip() throws IOException {
        if(testFormat()) {
            try {
                //Unzip le fichier zip
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("unzip " + PathProjet);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur dans la lecture du fichier zip");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Le fichier entré n'est pas au format '.zip', merci de verifier le nom du fichier a tester.");
        }
    }
}
