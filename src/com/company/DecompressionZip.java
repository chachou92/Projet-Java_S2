package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Gere la décompression du fichier Soumission.zip
 */
public class DecompressionZip {

    /**
     * Decompresse le fichier zip
     */
    public static void dezip() throws IOException {
        //Ouvre le fichier zip
        File file = new File(System.getProperty("user.dir") + "/Soumission.zip");
        InputStream in = new FileInputStream(file);
        //Cree un ZipInputsream pour lire le fichier zip
        ZipInputStream zipInputStream = new ZipInputStream(in);
        //Extrait les fichiers du repertoire Soumission
        ZipEntry ent = null;
        try {

            //Tant qu'il reste des fichiers dans le repertoire
            while ((ent = zipInputStream.getNextEntry()) != null) {

                // On cree un fichier
                String nom = ent.getName();
                File f = new File(System.getProperty("user.dir") + "/" + nom);
                InputStream is = new FileInputStream(f);
                //On ecrit dans ce fichier a partir du fichier zip
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line = "";
                String fichier = "";
                while (line != null) {
                    line = br.readLine();
                    fichier = fichier + line + "\n";
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la lecture du fichier");
        }
    }
}
