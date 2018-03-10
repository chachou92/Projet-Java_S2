package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Genere un fichier dans lequel apparait les resultats des tests.
 */
public class FichierResultats {
    public String nomRepProjet;

    /**
     * Constructeur de la classe FIchierResultat
     * @param nomRepertoireProjet
     */
    public FichierResultats (String nomRepertoireProjet) {
        this.nomRepProjet = nomRepertoireProjet;

    }
    public FichierResultats(){
        nomRepProjet = "";
    }

    /**
     * Genere un fichier contentant les resultats des tests unitaires.
     * @param tu
     * @return Le fichier ResultatsTestU.txt avec les résultats des tests.
     */
    public File fichierTestU (String [] tu) {
        File file;
        try {
            file = new File (System.getProperty("user.dir") + "/"+ nomRepProjet+"/ResultatsTestU.txt");
            file.createNewFile();
            Path path = Paths.get(System.getProperty("user.dir") + "/"+ nomRepProjet+"/ResultatsTestU.txt");
            if(!(Files.exists(path))){
                //On cree le fichier si il n'existe pas encore
                Files.createFile(path);
                //On ecrit le contenu de la liste dans le fichier
                Files.write(path, Collections.singleton(tu[0]));
            }
            else{
                //Si il existe deja, on reecrit le nouveau contenu de la liste dans le fichier de sortie
                Files.write(path,Collections.singleton(tu[0]));
            }
            return file;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println ("Erreur dans la creation du fichier de resultats.");
        }
        return null;



    }

    /**
     * Genere un fichier contentant les resultats des tests de performance.
     * @param tp
     * @return Le fichier ResultatsTestPerf.txt avec les résultats des tests.
     */
    public File fichierTestPerf (String [] tp) {
        File file;
        try {
            file = new File (System.getProperty("user.dir") + "/"+ nomRepProjet+"/ResultatsTestPerf.txt");
            file.createNewFile();
            Path path = Paths.get(System.getProperty("user.dir") + "/"+ nomRepProjet+"/ResultatsTestPerf.txt");
            if(!(Files.exists(path))){
                //On cree le fichier si il n'existe pas encore
                Files.createFile(path);
                //On ecrit le contenu de la liste dans le fichier
                Files.write(path, Collections.singleton(tp[0]));
            }
            else{
                //Si il existe deja, on reecrit le nouveau contenu de la liste dans le fichier de sortie
                Files.write(path,Collections.singleton(tp[0]));
            }
            return file;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println ("Erreur dans la creation du fichier de resultats.");
        }
        return null;



    }


}


