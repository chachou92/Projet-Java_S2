package com.company;

import java.io.*;

public class Execution {
    /**
     * Gere l'execution d'un fichier java
     */
    public String nomProgramme;

    String classpath;
    String classExecute;

    /**
     * Constructeur de la classe Execution
     * @param classpath
     * @param classExecute
     */


    public Execution(String classpath, String classExecute) {
        this.classpath = classpath;
        this.classExecute = classExecute;
    }

    /**
     * Execute le fichier
     * @throws IOException
     */
    public void execution() throws IOException {
        try{
            //Execute le projet de nom "nomProgrammet"
            Runtime runtime = Runtime.getRuntime();
            String[] t = new String[]{"java", "-classpath", classpath, classExecute};
            Process process = runtime.exec(t);
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
            System.out.println("Erreur dans la lecture du fichier class");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Calcule le temps d'execution du programme
     * @return le temps d'execution du programme
     * @throws IOException
     */
    public long tempeExecution () throws IOException{
        long debutExe = System.currentTimeMillis();
        execution();
        long finExe = System.currentTimeMillis();
        System.out.println(finExe - debutExe + " ms");
        return (finExe - debutExe);
    }

}

