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
            System.out.println("ici "+i + " " + nomFichiers[i]);
            if(nomFichiers[i].contains(".zip")){
                System.out.println("c'est la "+i + " " + nomFichiers[i]);
                DecompressionZip d = new DecompressionZip(nomFichiers[i]);
                d.dezip();
            }
        }
    }
}
