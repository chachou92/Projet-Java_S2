package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Gere la creation d'un fichier HTML contentant la synthetisation des resultats de l'etudiant.
 */
public class PageHtml {
    public String nomRepProjet;

    /**
     * Constructeur de la classe FIchierResultat
     * @param nomRepertoireProjet
     */
    public PageHtml (String nomRepertoireProjet) {
        this.nomRepProjet = nomRepertoireProjet;

    }
    public PageHtml(){
        nomRepProjet = "";
    }

    /**
     * Genere un fichier contenant la synthetisation des resultats.
     * @param testU
     * @param testPerf
     * @return Le fichier .html avec le contenu de la page HTML qui regroupe les resultats du projet.
     */
    public File fichierHTML(String[] testU, String[] testPerf){
        File file;
        String testUbis = testU[0].replaceAll("\n","<br>");
        String testPerfbis = testPerf[0].replaceAll("\n","<br>");
        String txt = "<!doctype html><html lang=\"fr\"><head><meta charset=\"utf-8\"><title>"+nomRepProjet+"</title><link rel=\"stylesheet\" href=\"style.css\"><script src=\"script.js\"></script></head><body><br>"+"Resultats des tests unitaires:<br><br>"+testUbis+"<br>Resultats des tests de performance:<br><br>"+ testPerfbis+"<br></body></html>";
        byte[] bytes = txt.getBytes();

        try {
            file = new File (System.getProperty("user.dir") + "/"+ nomRepProjet+"/Resultats.html");
            file.createNewFile();
            Path path = Paths.get(System.getProperty("user.dir") + "/"+ nomRepProjet+"/Resultats.html");
            if(!(Files.exists(path))){
                //On cree le fichier si il n'existe pas encore
                Files.createFile(path);
                //On ecrit le contenu de la liste dans le fichier
                Files.write(path, bytes);
            }
            else{
                //Si il existe deja, on reecrit le nouveau contenu de la liste dans le fichier de sortie
                Files.write(path,bytes);
            }
            return file;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println ("Erreur dans la creation du fichier de resultats.");
        }
        return null;

    }

}
