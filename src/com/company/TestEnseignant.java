package com.company;

import java.io.*;

/**
 * Gere l'execution des tests unitaires et des tests de performance de l'enseignant
 */
public class TestEnseignant {

    String nomRepertoire;

    /**
     * Constructeur de la classe TestEnseignant
     * @param nomRep
     */
    public TestEnseignant (String nomRep){
        nomRepertoire = nomRep;
    }

    public TestEnseignant (){
        nomRepertoire = "";
    }

    /**
     * Compile les classes contenant les tests unitaires du repertoire
     */
    public void compileTest (String repertoire) throws IOException {

        try{
            //Compile les tests unitaires
            Runtime runtime = Runtime.getRuntime();
            String[] str = {"/bin/sh","-c", "javac -cp "+repertoire+"/"+nomRepertoire+"/junit-4.12.jar:"+repertoire+"/"+nomRepertoire+"/hamcrest-core-1.3.jar:"+repertoire+"/"+nomRepertoire+" "+repertoire+"/"+nomRepertoire+"/*.java" };
            Process process = runtime.exec(str);
            process.waitFor();
            //Affiche la sortie standard du programmme
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String l;
            while((l = reader.readLine()) != null){
                System.out.println(l);
            }
            reader.close();
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
            System.out.println("Erreur dans la lecture du fichier .java");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute les classes contenant les tests unitaires du repertoire
     */
    public String[] executeTest (String[] listeTests, String repertoire) throws IOException{
        String[] tab = new String[2];
        try{
            //Execute le projet de nom "nomProgrammet"
            Runtime runtime = Runtime.getRuntime();
            for (int i = 0; i < listeTests.length; i++){
                if (listeTests[i].contains(".java")){
                    String aux = listeTests[i].replace(".java", "");
                    String[] str = {"/bin/sh","-c", "java -cp "+repertoire+"/"+nomRepertoire+"/junit-4.12.jar:"+repertoire+"/"+nomRepertoire+"/hamcrest-core-1.3.jar:"+repertoire+"/"+nomRepertoire+" org.junit.runner.JUnitCore "+repertoire+"/"+nomRepertoire+"/"+aux};
                    Process process = runtime.exec(str);
                    process.waitFor();
                    //Affiche la sortie standard du programmme
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String l;
                    String inputstring = "";
                    while((l = reader.readLine()) != null){
                        //System.out.println(l);
                        inputstring+=l + "\n";
                    }
                    tab[0] = inputstring;
                    reader.close();
                    //Affiche la sortie d'erreur
                    InputStream in = new ByteArrayInputStream(process.getErrorStream().readAllBytes());
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String line;
                    String errorstring = "";
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        errorstring+=line + "\n";
                    }
                    tab[1] = errorstring;
                    br.close();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la lecture du fichier .java");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tab;
    }


}
