package com.company;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Gere l'execution du programme de test.
 */
public class Main {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //On parcourt tous les fichiers et on unzip les fichiers .zip
        File repertoire = new File(System.getProperty("user.dir"));
        String[] nomFichiers = repertoire.list();
        String nomRep;
        nomRep = JOptionPane.showInputDialog("Comment voulez-vous nommer le répertoire une fois dézippé?");
        File r = new File(System.getProperty("user.dir") + "/" + nomRep);
        while (r.exists()) {
            JOptionPane.showMessageDialog(null, "Ce dossier existe déjà, merci de choisir un autre nom.", "Projet Java", JOptionPane.INFORMATION_MESSAGE);
            nomRep = JOptionPane.showInputDialog("Comment voulez-vous nommer le répertoire une fois dézippé?");
            r = new File(System.getProperty("user.dir") + "/" + nomRep);
        }

        String nom = "";
        for (int i = 0; i < nomFichiers.length; i++) {
            if (nomFichiers[i].contains(".zip")) {
                DecompressionZip d = new DecompressionZip(nomFichiers[i], nomRep);
                d.dezip();
                nom = nomFichiers[i].replace(".zip", "");
            }
        }

        /*File proj = new File(System.getProperty("user.dir")+"/"+nomRep+"/"+nom);
        String[] listeF = proj.list();

        //On genere une javadoc
        try{
            for (int i=0;i<listeF.length;i++){
                String str = listeF[i];
                System.out.println(str);
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("javadoc -d "+proj.getPath()+ " -sourcepath "+proj.getPath()+" "+listeF[i]);
                process.waitFor();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur pour generer la javadoc");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        //On compile les fichiers
        String nomrepertoireTestPerf = JOptionPane.showInputDialog("Comment se nomme le dossier contenant les tests de performance?");
        File testsP = new File(System.getProperty("user.dir") + "/" + nomrepertoireTestPerf);
        while (!(testsP.exists())){
            JOptionPane.showMessageDialog(null, "Ce dossier n'existe pas, merci de choisir un autre nom.", "Projet Java", JOptionPane.INFORMATION_MESSAGE);
            nomrepertoireTestPerf = JOptionPane.showInputDialog("Comment se nomme le dossier contenant les tests de performance?");
            testsP = new File(System.getProperty("user.dir") + "/" + nomrepertoireTestPerf);
        }
        String[] testsPList = testsP.list();

        if (testsP.exists()) {
            for (int i = 0; i < testsPList.length; i++) {
                Path src = Paths.get(Paths.get("./") + "/" + nomrepertoireTestPerf + "/" + testsPList[i]);
                Path nv = Paths.get("./" + nomRep + "/" + nom);
                Files.copy(src, nv.resolve(src.getFileName()));

            }
        }
        String nomrepertoireTest = JOptionPane.showInputDialog("Comment se nomme le dossier contenant les tests unitaires?");
        File tests = new File(System.getProperty("user.dir") + "/" + nomrepertoireTest);
        while (!(tests.exists())){
            JOptionPane.showMessageDialog(null, "Ce dossier n'existe pas, merci de choisir un autre nom.", "Projet Java", JOptionPane.INFORMATION_MESSAGE);
            nomrepertoireTest = JOptionPane.showInputDialog("Comment se nomme le dossier contenant les tests unitaires?");
            tests = new File(System.getProperty("user.dir") + "/" + nomrepertoireTest);
        }
        String[] testsList = tests.list();
        if (tests.exists()) {
            TestEnseignant t = new TestEnseignant(Paths.get("./") + "/" + nomrepertoireTest);
            for (int i = 0; i < testsList.length; i++) {
                Path src = Paths.get(t.nomRepertoire + "/" + testsList[i]);
                Path nv = Paths.get("./" + nomRep + "/" + nom);
                Files.copy(src, nv.resolve(src.getFileName()));
            }
            TestEnseignant nw = new TestEnseignant(nom);
            nw.compileTest(nomRep);

        }


        File projets = new File(System.getProperty("user.dir") + "/" + nomRep);
        String[] nomProjets = projets.list();

        //TESTS UNITAIRES
        int repTest;
        String[] tabU = {};
        repTest = JOptionPane.showConfirmDialog(null, "Voulez-vous exécuter des tests unitaires?", "Projet Java", JOptionPane.YES_NO_OPTION);

        if (repTest == JOptionPane.YES_OPTION) {
            TestEnseignant testU = new TestEnseignant(nom);
            tabU = testU.executeTest(testsList, nomRep);
            System.out.println(tabU[0]);

        } else {
            JOptionPane.showMessageDialog(null, "Vous avez décidé de ne pas exécuter de test unitaire.");
        }

        //On demande si on veut executer le programme, et si oui quelle classe doit etre execute
        int rep;
        long tmps = 0;
        rep = JOptionPane.showConfirmDialog(null, "Voulez-vous exécuter le projet de l'étudiant?", "Projet Java", JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION) {
            //On execute la classe Main associée au projet
            for (int i = 1; i < nomProjets.length; i++) {
                File repCourant = new File(System.getProperty("user.dir") + "/" + nomRep);
                String p = repCourant.getPath() + "/" + nomProjets[i];
                //String[] liste = repCourant.list();
                String nomClass = JOptionPane.showInputDialog("Quelle classe voulez-vous exécuter?");
                Execution e = new Execution(p, nomClass);
                tmps= e.tempeExecution();
            }
        }
        if (rep == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Vous avez décidé de ne pas exécuter le programme.");
        }

        //TEST PERFORMANCE
        int repPerf;
        String[] tab={};
        repPerf = JOptionPane.showConfirmDialog(null, "Voulez-vous exécuter les tests de performance?", "Projet Java", JOptionPane.YES_NO_OPTION);
        if (repPerf == JOptionPane.YES_OPTION) {
            for (int i = 0; i < testsPList.length; i++) {
                testsPList[i] = testsPList[i].replace(".java", "");
            }
            for (int i = 0; i < testsPList.length; i++) {
                File repCourant = new File(System.getProperty("user.dir") + "/" + nomRep);
                String p = repCourant.getPath() + "/" + nom;
                Execution e = new Execution(p, testsPList[i]);
                tab = e.execution();
                System.out.println(tab[0]);
            }
        }
        if (rep == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Vous avez décidé de ne pas exécuter les tests de performance.");
        }

        //FICHIERS DE SORTIE
        FichierResultats fr = new FichierResultats(nomRep);
        fr.fichierTestU(tabU);
        fr.fichierTestPerf(tab);

        //PAGE HTML
        PageHtml pageHtml=new PageHtml(nomRep);
        pageHtml.fichierHTML(tabU,tab,tmps);


    }

}
