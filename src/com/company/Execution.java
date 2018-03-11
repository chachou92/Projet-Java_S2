package com.company;

import java.io.*;
/**
 * Gere l'execution d'un fichier java
 */
public class Execution {

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
    public String[] execution() throws IOException {
        String[] tab = new String[2];
        try{
            //Execute le projet de nom "nomProgrammet"
            Runtime runtime = Runtime.getRuntime();
            String[] t = new String[]{"java", "-classpath", classpath, classExecute};
            Process process = runtime.exec(t, null, new File(classpath));
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

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la lecture du fichier class");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tab;

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

