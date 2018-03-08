package com.company;

import java.io.*;

/**
 * Gere l'execution des tests unitaires et des tests de performance de l'enseignant
 */
public class TestEnseignant {

    String nomRep;

    /**
     * Constructeur de la classe TestEnseignant
     * @param nomRepertoire
     */
    public TestEnseignant (String nomRepertoire){
        nomRep = nomRepertoire;
    }

    public TestEnseignant (){
        nomRep = "";
    }

    /**
     * Compile les classes contenant les tests unitaires du repertoire
     */
    public void compileTest () throws IOException {

        try{
            //Execute le projet de nom "nomProgrammet"
            Runtime runtime = Runtime.getRuntime();
            String[] str = {"/bin/sh","-c", "javac -cp "+nomRep+"/junit-4.12.jar:"+nomRep+"/hamcrest-core-1.3.jar:"+nomRep+" "+nomRep+"/*.java" };
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


}
