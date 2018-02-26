package com.company;

import java.io.File;
import java.io.IOException;

public class Main {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //On parcourt tous les fichiers et on unzip les fichiers .zip
        File repertoire = new File(System.getProperty("user.dir"));
        String[] nomFichiers = repertoire.list();
        for (int i =0; i<nomFichiers.length;i++){
            if(nomFichiers[i].contains(".zip")){
                DecompressionZip d = new DecompressionZip(nomFichiers[i]);
                d.dezip();
            }
        }
        File Projets = new File(System.getProperty("TestProjets"));
        String [] nomProjets = Projets.list();
        for (int i = 0; i < nomProjets.length; i++){ //tester s'il y a autres choses que des .java et demander au prof du coup spj bb
            File repCourant = new File(System.getProperty(nomProjets[i]));
            String[] nomFichiersCourants = repCourant.list();
            for (int j = 0; j < nomFichiersCourants.length; j++){
                if(nomFichiersCourants[j].contains(".java")){
                    Compilation c = new Compilation(nomFichiersCourants[j]);
                    c.compiler();
                }
            }
        }
    }
}
