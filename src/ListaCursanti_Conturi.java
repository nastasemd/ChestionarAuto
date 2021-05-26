import java.util.*;
import java.io.*;

public class ListaCursanti_Conturi {
    private ArrayList<Cursant> cursanti = new ArrayList<>();
    private ArrayList<String> id = new ArrayList<>();
    
    public ListaCursanti_Conturi(){
        //citesc din fisier toate conturile, in caz ca s-au creat in executia trecuta, si le salvez in lista cursanti si lista id.
        try{
            BufferedReader in = new BufferedReader(new FileReader("cursanti.txt"));
            String linie;
            while((linie=in.readLine())!=null){
                String[] e = linie.split(" ");
                Cursant c = new Cursant(e[0], e[1], e[2], e[3], e[4]);
                adaugaCursant(c);
                adaugaID(c);
            }
            in.close();
            
        }catch(IOException e){System.err.println(e.getMessage());}
    } 
    
    public void adaugaCursant(Cursant c){
        cursanti.add(c);
    }
    
    public void adaugaID(Cursant c){
        id.add(c.getID());
    }
    
    public void scriereFisiere(){
    //o apelez atunci cand vreau sa rescriu fisierul cursanti si id
    //in cazul actual, cand creez un cont nou(Inregistrae)
        try{
            BufferedWriter c = new BufferedWriter(new FileWriter("cursanti.txt"));
            BufferedWriter i = new BufferedWriter(new FileWriter("ID.txt"));
            for(Cursant x:cursanti){
                c.write(x.toString());
                c.newLine();
                i.write(x.getID());
                i.newLine();
            }
            c.close();
            i.close();
        }catch(IOException e){System.err.println(e.getMessage());}
    } 
    public boolean verificareID(String x){
        //vad daca id introdus este la fel cu id-ul unui cont existent
        for(int i = 0; i < id.size(); i++){
            if(x.equals(id.get(i)))
                return true;
        }
        return false;
    }
    
    public boolean autentificareCursanti(String i, String p){
        //verific daca id-ul si parola sunt asociate unui cont 
        for(Cursant c : cursanti){
            if(c.getID().equals(i) && c.getParola().equals(p))
                return true;
        }
        return false;
    }
    
    //Pentru interfata Administrator
    public void modificaCursant(String nume, String prenume, String id, String parola, String categorie, String index){
        int i = Integer.parseInt(index);
        cursanti.get(i).setNume(nume);
        cursanti.get(i).setPrenume(prenume);
        cursanti.get(i).setID(id);
        cursanti.get(i).setParola(parola);
        cursanti.get(i).setCategorie(categorie);
        scriereFisiere();
        ListaCursanti_Conturi l = new ListaCursanti_Conturi();
    }
    
    public void stergeCursant(String index){
        File f = new File("teste");
        File[] fisiere = f.listFiles();
        for(File element:fisiere){
            String[] e = element.getName().split("_");
            if(e[0].equals(cursanti.get(Integer.parseInt(index)).getID())){
                System.out.println("Am trecut pe aici!" + element.getPath());
                element.delete();
            }       
        }
        int i = Integer.parseInt(index);
        cursanti.remove(i);
        scriereFisiere();
        ListaCursanti_Conturi l = new ListaCursanti_Conturi(); 
    }
    
    public String[][] cautaCursant(String cautare){
        ArrayList<Cursant> caut = new ArrayList<>();
        int[] index = new int[cursanti.size()];
        int j = 0;
        if(!cautare.isEmpty()){           
            for(int i = 0; i < cursanti.size(); i++){
                if(cursanti.get(i).getID().equals(cautare) || cursanti.get(i).getID().contains(cautare)){
                    index[j] = i;
                    j++;
                    caut.add(cursanti.get(i));
                }        
            }     
        }else{
            caut = cursanti;
            for(int i = 0; i < caut.size(); i++)
                index[i] = i;
        }
        if(!caut.isEmpty()){
                String[][] rez = new String[caut.size()][6];
                for(int i = 0; i < caut.size(); i++){
                    rez[i][0] = Integer.toString(index[i]);
                    rez[i][1] = caut.get(i).getNume();
                    rez[i][2] = caut.get(i).getPrenume();
                    rez[i][3] = caut.get(i).getID();
                    rez[i][4] = caut.get(i).getParola();
                    rez[i][5] = caut.get(i).getCategorie();
                }
                return rez;
            }
            String[][] e = new String[1][6];
            return e; 
    }
}