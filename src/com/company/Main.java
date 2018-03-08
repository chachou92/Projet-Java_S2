package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /*
        //On parcourt tous les fichiers et on unzip les fichiers .zip
        File repertoire = new File(System.getProperty("user.dir"));
        String[] nomFichiers = repertoire.list();
        Scanner scan = new Scanner(System.in);
        System.out.println("Comment voulez-vous nommer le répertoire une fois dézippé?");
        String nomRep = scan.nextLine();
             for (int i = 0; i < nomFichiers.length; i++) {
                 if (nomFichiers[i].contains(".zip")) {
                     DecompressionZip d = new DecompressionZip(nomFichiers[i], nomRep);
                     d.dezip();
                 }
        }
        //On compile les fichiers .java
        File projets = new File(System.getProperty("user.dir") + "/" + nomRep);
        String[] nomProjets = projets.list();
        for (int i = 1; i < nomProjets.length; i++) { //tester s'il y a autres choses que des .java et demander au prof du coup
            File repCourant = new File(System.getProperty("user.dir")+"/" + nomRep+"/"+nomProjets[i]);
            String[] nomFichiersCourants = repCourant.list();
            for (int j = 0; j < nomFichiersCourants.length; j++) {
                if (nomFichiersCourants[j].contains(".java")) {
                    Compilation c = new Compilation(Paths.get("./")+"/" + nomRep+"/"+nomProjets[i]+"/"+nomFichiersCourants[j]);
                    c.compiler();
                }
            }
        }
        //On execute la classe Main associée au projet
        for (int i = 1; i < nomProjets.length; i++){
            File repCourant = new File (System.getProperty("user.dir") + "/" + nomRep);
            String p = repCourant.getPath()+"/"+nomProjets[i];
            String[] liste = repCourant.list();
            Execution e = new Execution(p, "Main");
            e.tempeExecution();
        }
    }*/

        //Demander si on veut executer des test Uni puis des Test Perf dans un menu
        //On compile les fichiers de test
        File tests = new File(System.getProperty("user.dir") + "/TestPro");
        TestEnseignant t = new TestEnseignant(Paths.get("./")+"/TestPro");
        //t.compileTest();
        String[] testsList = tests.list();
        for (int i = 0; i < testsList.length; i++){
            Path src = Paths.get(testsList[i].toString());
            System.out.println(src.toString());
            Path nv= Paths.get(System.getProperty("user.dir") + "/AAA");
            Files.copy(src, nv.resolve(src.getFileName()));
        }

      /*  //On copie les fichiers test et on les deplace vers le dossier du projet etudiant
        Path src = Paths.get(tests.toString());
        Path nv= Paths.get(System.getProperty("user.dir") + "/OrdreAlfabetix");
        Files.copy(src, nv.resolve(src.getFileName()));*/

    }



}
