import java.io.*;
import java.util.*;

public class ListaIntrebari {
    private ArrayList<Intrebare> listaIntrebari;
    
    public ListaIntrebari(){
        listaIntrebari = new ArrayList<>();
    }
    
    public Intrebare getIntrebare(int i){
        return listaIntrebari.get(i);
    }
    
    public void adaugaIntrebare(String i, String a, String b, String c, String r){
        try{//Adauga intrebarea in fisierul cu intrebari
            BufferedWriter bw = new BufferedWriter(new FileWriter("intrebari.txt",true));
            bw.write(i + "\n" + a + "\n" + b + "\n" + c + "ln" + r + "\n");
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void citesteIntrebari(){//Citeste intrebarile pentru chestionar nou
        int linii = 0;
        try{//Ciitim numarul de linii din fisier
            BufferedReader reader = new BufferedReader(new FileReader("intrebari.txt"));
            while (reader.readLine() != null)
                linii++;
            reader.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        int nrIntrebari = linii/5;//Numarul de intrebari = nr linii din fisier/5
        int n = 0,r;
        int numere[] = new int[26];
        boolean OK = true;
        while(n < 26)//Luam 26 de numere aleator(fiecare nr diferit de celelalte)
        {
            do{
                OK = true;
                r = (int)(Math.random() * nrIntrebari);
                for(int i = 0; i < n;i++)//Verificam daca numarul ales aleator nu mai fusese ales deja
                    if(numere[i] == r)
                        OK = false;
            }while(!OK);
            numere[n] = r;//Salvam numarul ales aleator
            n++;
        }
        for(int i = 0;i < 26;i++){//Citim cele 26 de intrebari din fisier
            try{
                int j = 0;
                BufferedReader br = new BufferedReader(new FileReader("intrebari.txt"));
                while(j < numere[i] * 5){//Pentru a ajunge la intrebarea numere[i] parcurgem numere[i]*5 linii din fisier
                    br.readLine();
                    j++;
                }
                String textIntrebare = br.readLine();//Urmatoarea linie contine intrebarea propriu-zisa
                String textRaspunsA = br.readLine();//Urmatoarele 3 linii contin cele 3 variante de raspuns
                String textRaspunsB = br.readLine();
                String textRaspunsC = br.readLine();
                Intrebare aux = new Intrebare(textIntrebare,textRaspunsA,textRaspunsB,textRaspunsC);//Cream o noua intrebare
                listaIntrebari.add(aux);//O salvam in vectorul de intrebari
                listaIntrebari.get(i).setNumarIntrebare(numere[i]);//Salvam numarul intrebarii(necesar pentru imagine)
                String raspunsuriCorecte = br.readLine();//Urmatoarea linie contine raspunsurile corecte
                if(raspunsuriCorecte.contains("A"))//Setam raspunsurile corecte
                    listaIntrebari.get(i).setCorectA();
                if(raspunsuriCorecte.contains("B"))
                    listaIntrebari.get(i).setCorectB();
                if(raspunsuriCorecte.contains("C"))
                    listaIntrebari.get(i).setCorectC();
                br.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
    
    public void citesteIntrebari(String numeFisier){//Citim intrebarile pentru un chestionar inceput/terminat
        String intrebarile = "";
        String[] numereIntrebari;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(numeFisier));
            intrebarile = reader.readLine();//Prima linie din fisier contine nr fiecarei intrebari din chestionarul respectiv
            reader.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        numereIntrebari = intrebarile.split(" ");//Separam numerele de pe aceea linie
        for(int i = 0;i < 26;i++){//Citim cele 26 de intrebari din fisier
            int c = Integer.parseInt(numereIntrebari[i]);//Transformam din String in int
            try{
                int j = 0;
                BufferedReader br = new BufferedReader(new FileReader("intrebari.txt"));
                while(j < c * 5){//Pentru a ajunge la intrebarea c, parcurgem c*5 linii din fisier
                    br.readLine();
                    j++;
                }
                String textIntrebare = br.readLine();//Urmatoarea linie contine intrebarea propriu-zisa
                String textRaspunsA = br.readLine();//Urmatoarele 3 contin cele 3 variante de raspuns
                String textRaspunsB = br.readLine();
                String textRaspunsC = br.readLine();
                //Urmatoarea contine raspunsurile corecte
                Intrebare aux = new Intrebare(textIntrebare,textRaspunsA,textRaspunsB,textRaspunsC);//Cream o noua intrebare
                listaIntrebari.add(aux);//O salvam in vectorul de intrebari
                listaIntrebari.get(i).setNumarIntrebare(c);//Salvam numarul intrebarii(necesar pentru imagine)
                String raspunsuriCorecte = br.readLine();//Urmatoarea linie contine raspunsurile corecte
                if(raspunsuriCorecte.contains("A"))//Setam raspunsurile corecte
                    listaIntrebari.get(i).setCorectA();
                if(raspunsuriCorecte.contains("B"))
                    listaIntrebari.get(i).setCorectB();
                if(raspunsuriCorecte.contains("C"))
                    listaIntrebari.get(i).setCorectC();
                br.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
}
