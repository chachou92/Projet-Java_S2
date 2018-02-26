import java.io.*;

public class Compilation {
    /**
     *Gere la compilation d'un projet java
     */
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

    /**
     * Verifie si le fichier est au format ".java"
     * @return true si le fichier est au format .java, false sinon
     */
    public boolean testFormat(){
        return (nomProjet.contains(".java"));
    }

    /**
     * Compile tous les fichiers '.java' qui sont dans le projet
     * @throws IOException
     */
    public void compiler() throws IOException {
        if(testFormat()){
            try{
                //Compile le projet de nom "nomProjet"
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("javac -d TestProjet" + nomProjet+"/*.java");
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
        else {
            System.out.println("Le fichier entré n'est pas au format '.java', merci de verifier le nom du fichier a copmiler.");
        }
    }



}
