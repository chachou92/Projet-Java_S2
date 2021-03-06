package com.company;

import java.io.*;

/**
 *Gere la compilation d'un projet java
 */
public class Compilation {


    public String nomProjet;

    /**
     * Constructeur de la classe Compilation
     * @param nom
     */
    public Compilation (String nom){
        nomProjet = nom;
    }
    public Compilation (){
        nomProjet = "";
    }

     /** Compile les fichiers Java
     * @throws IOException
     */
    public void compiler() throws IOException {
            try{
                //Compile le projet de nom "nomProjet"
                Runtime runtime = Runtime.getRuntime();
                String [] s = {"/bin/sh","-c", "javac" , nomProjet ,"*.java"};
                Process process = runtime.exec(s);
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
                System.out.println("Erreur dans la lecture du fichier java");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
