import java.io.*;
import java.awt.*;
import java.util.*;

public class GestorFisiere {
    private String numeFisier;
    private String[] raspunsuriA,raspunsuriB,raspunsuriC;
    
    public GestorFisiere(String s){
        numeFisier = s;
        raspunsuriA = new String[26];
        raspunsuriB = new String[26];
        raspunsuriC = new String[26];
        Arrays.fill(raspunsuriA,"D");//Setam starea butoanelor ca default, pentru toate intrebarile
        Arrays.fill(raspunsuriB,"D");
        Arrays.fill(raspunsuriC,"D");
    }
    
    public String getCuloareA(int i){
        return raspunsuriA[i];
    }
    
    public String getCuloareB(int i){
        return raspunsuriB[i];
    }
    
    public String getCuloareC(int i){
        return raspunsuriC[i];
    }
    
    public void copiaza(ListaIntrebari listaIntrebari){//Copiem numerele celor 26 de intrebari
        try{
            PrintWriter out = new PrintWriter(new FileWriter(numeFisier,true));
            for(int i = 0; i < 25;i++)//Numarul primelor 25 de intrebari separate printr-un spatiu
                out.print(listaIntrebari.getIntrebare(i).getNumarIntrebare() + " ");
            out.print(listaIntrebari.getIntrebare(25).getNumarIntrebare() + "\n"); //Numarul ultimei intrebari si sfarsit de rand
            out.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void copiaza(int i, Color culoareButonA, Color culoareButonB, Color culoareButonC){
        try{//Copiem nr intrebarii si fundalul butoanelor(R=raspuns gresit,V=raspuns corect,G=raspuns corect neales,D=default)
            PrintWriter out = new PrintWriter(new FileWriter(numeFisier,true));
            Color rosu = Color.RED;
            Color verde = Color.GREEN;
            Color gri = Color.GRAY;
            out.print(i + " ");//Numarul intrebarii(Necesar in caz ca sarim peste o intrebare si raspundem la una din urmatoarele)
            if(culoareButonA == rosu)//Pentru raspunsul A 
               out.print("R ");
            else if(culoareButonA == verde)
                out.print("V ");
            else if(culoareButonA == gri)
                out.print("G ");
            else
                out.print("D ");//default
            if(culoareButonB == rosu)//Pentru raspunsul B
                out.print("R ");
            else if(culoareButonB == verde)
                out.print("V ");
            else if(culoareButonB == gri)
                out.print("G ");
            else
                out.print("D ");
            if(culoareButonC == rosu)//Pentru raspunsul C
                out.print("R");
            else if(culoareButonC == verde)
                out.print("V");
            else if(culoareButonC == gri)
                out.print("G");
            else
                out.print("D");
            out.print("\n");
            out.flush();
            out.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void copiaza(int raspunsuriCorecte, int raspunsuriGresite){
        try{//Copiem nr raspunsuri corecte si gresite
            PrintWriter out = new PrintWriter(new FileWriter(numeFisier,true));
            out.write(raspunsuriCorecte + " " + raspunsuriGresite);
            out.flush();
            out.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void copiaza(String s){
        try{//Pentru nepromovat
            PrintWriter out = new PrintWriter(new FileWriter(numeFisier,true));
            out.write(s + "\n");
            out.flush();
            out.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public String citesteRaspunsuri(){//Pentru chestionar rezolvat complet
        String str = "",s = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(numeFisier));
            s = reader.readLine();
            int i = 0;
            while(i < 26){
                s = reader.readLine();
                if(s.equals("Nepromovat"))//Daca dam peste "Nepromovat"
                    break;
                String[] s1 = s.split(" ");
                int n = Integer.parseInt(s1[0]);
                raspunsuriA[n] = s1[1];
                raspunsuriB[n] = s1[2];
                raspunsuriC[n] = s1[3];
                i++;
            }
            if(s.equals("Nepromovat")){//Daca am gasit "Nepromovat" citim linia urmatoare cu nr raspsunuri corecte/gresite
                str += s + " ";//Salvam "Nepromovat "  
            }
            s = reader.readLine();
            str += s;//Adaugam nr raspunsurilor corecte si gresite
            reader.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return str;
    }
    
    public void citesteRaspunsuriPartiale(){//Pentru cel neterminat
        try{
            BufferedReader reader = new BufferedReader(new FileReader(numeFisier));
            reader.readLine();
            String s = reader.readLine();
            while(s != null){//Citim raspunsurile
                String[] s1 = s.split(" ");
                int n = Integer.parseInt(s1[0]);
                raspunsuriA[n] = s1[1];
                raspunsuriB[n] = s1[2];
                raspunsuriC[n] = s1[3];
                s = reader.readLine();
            }
            reader.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
