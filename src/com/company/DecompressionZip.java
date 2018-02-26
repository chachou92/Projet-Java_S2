package com.company;

import java.io.*;
import java.lang.Runtime;

/**
 * Gere la décompression du fichier Soumission.zip
 */
public class DecompressionZip {
    public  String pathProjet;

    /**
     * Constructeur de la classe DecompressionZip
     * @param nom
     */
    public DecompressionZip(String nom){
        pathProjet = nom;
    }
    public DecompressionZip(){
        pathProjet = "";
    }

    /**
     * Verifie si le fichier est au format ".zip"
     * @return true si le fichier est au format .zip, false sinon
     */
    public boolean testFormat(){
        return pathProjet.contains(".zip");
    }

    /**
     * Decompresse le fichier zip
     */
    public void dezip() throws IOException {
        if(testFormat()) {
            try {
                //Unzip le fichier zip
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("unzip -d TestProjets " + pathProjet);
                process.waitFor();
                //Affiche la sortie d'erreur
                InputStream in = new ByteArrayInputStream(process.getErrorStream().readAllBytes());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                br.close();

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
