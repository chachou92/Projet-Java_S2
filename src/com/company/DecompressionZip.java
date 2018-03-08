package com.company;

import javax.print.DocFlavor;
import java.io.*;
import java.lang.Runtime;
import java.nio.file.Path;

/**
 * Gere la décompression du fichier Soumission.zip
 */
public class DecompressionZip {
    public  String pathProjet;
    public String repertoire;

    /**
     * Constructeur de la classe DecompressionZip
     * @param nom
     */
    public DecompressionZip(String nom, String rep){
        pathProjet = nom;
        repertoire = rep;
    }
    public DecompressionZip(){
        pathProjet = "";
        repertoire="";
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
                Process process = runtime.exec("unzip -d "+ repertoire + " " +pathProjet);
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
            catch (NullPointerException n){
                n.printStackTrace();
            }
        }
        else {
            System.out.println("Le fichier entré n'est pas au format '.zip', merci de verifier le nom du fichier a tester.");
        }
    }

}
