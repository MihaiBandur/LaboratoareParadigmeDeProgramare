package  Exercitiul2;
import java.io.*;


public class main {
    public static void main(String[] args)throws IOException{
        String fisier = "fisier.txt";

        File file = new File(fisier);
        if (!file.exists()) {
            System.err.println("Fișierul " + fisier + " nu există.");
            return;
        }

        try {
            String continut = citesteFisier(fisier);
            System.out.println("Conținutul fișierului:");
            System.out.println(continut);
            String faraPunctuație = eliminarePunctuatie(continut);
            System.out.println("\nConținutul fără punctuație:");
            System.out.println(faraPunctuație);
            String litereMici=LitereMici(faraPunctuație);
            System.out.println("\nContinutul cu litere mici");
            System.out.println(litereMici);
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }
    static String citesteFisier(String fisier) throws IOException{
        StringBuilder text=new StringBuilder();
        try(BufferedReader reader =new BufferedReader(new FileReader(fisier))){
            String linie;
            while ((linie=reader.readLine())!=null){
                text.append(linie).append('\n');
            }
        }
        return text.toString();
    }
    static String eliminarePunctuatie(String text){
        StringBuilder rez=new StringBuilder();
        for(int i=0;i<text.length();i++)
        {
            Character ch=text.charAt(i);
            if(ch != ',' && ch != '?' && ch != '!' &&
                    ch != ':' && ch != '.' &&
                    ch != ';' && ch != '\'' &&
                    ch != '$' &&
                    ch != '%' && ch != '^'  &&
                    ch != '(' && ch != ')' &&
                    ch != '#' && ch != '@' &&
                    ch != '[' && ch != ']'){

                rez.append(ch);
            }
        }
        return  rez.toString();
    }
    static String LitereMici(String text){
        return text.toLowerCase();
    }
}