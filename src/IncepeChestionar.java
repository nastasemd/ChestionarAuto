import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class IncepeChestionar extends JFrame{
    private String utilizator,tip;
    private String nFisier = "";
    JButton bChestionarNou, bContinuaChestionar;
    JPanel p1;
    
    public IncepeChestionar(String u,String t){
        super("Chestionar tip " + t);
        utilizator = u;
        tip = t;
        AscultatorButoane a = new AscultatorButoane();
        
        bChestionarNou = new JButton("Chestionar nou");
        bChestionarNou.addActionListener(a);
        bContinuaChestionar = new JButton("Continua Chestionar");
        bContinuaChestionar.addActionListener(a);
        
        p1 = new JPanel();
        p1.add(bChestionarNou);
        p1.add(bContinuaChestionar);
        getContentPane().add(p1, BorderLayout.NORTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public boolean existaChestionarInceput(){
        File fisier = new File("teste");
        File[] listaFisiere = fisier.listFiles();
        //Ordonam fisierele descrescator dupa data modificarii(crearii in cazul nostru)
        Arrays.sort(listaFisiere, Comparator.comparingLong(File::lastModified).reversed());
        if(!fisier.isDirectory())
            System.out.println("Nu s-a gasit directorul \"teste\"");
        else{               
            for(int i = 0; i < listaFisiere.length; i++){
                String numeFisier = listaFisiere[i].getName();
                String[] elemente = numeFisier.split("_");
                if(elemente[0].equals(utilizator) && elemente[1].equals(tip)){
                    nFisier = "teste/" + numeFisier;
                    try{//Vedem daca ultimul test facut de utilizator este terminat sau nu
                        BufferedReader in = new BufferedReader(new FileReader(nFisier));
                        String s = in.readLine();
                        int c = 1;
                        while (s != null){
                            if(s.equals("Nepromovat"))
                                return false;
                            s = in.readLine();
                            c++;
                        }
                        in.close();
                        if(c == 28)//Daca chestionarul cel mai recent este facut complet
                           return false;
                        return true;//Daca nu este facut complet
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
        }
        return false;//Nu a mai facut nici un chestionar pana acum
    }
    
    class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if(ev.getSource() == bChestionarNou){
                if(!existaChestionarInceput()){//Daca nu exista chestionar inceput cream unul nou
                    setVisible(false);
                    ChestionarNou z = new ChestionarNou(utilizator,tip);
                    z.setSize(1000,400);
                }
                else{//Daca exista un chestionar inceput si vrem sa incepem altul
                    JOptionPane.showMessageDialog(null, "Exista deja un test neterminat. Continua acel test!","Eroare", JOptionPane.CLOSED_OPTION);
                }
            }else{
                if(existaChestionarInceput()){//Daca exista un chestionar inceput il continuam
                    setVisible(false);
                    ChestionarNeterminat z = new ChestionarNeterminat(nFisier,tip);
                    z.setSize(1000,400);
                }
                else{//Daca nu exista un chestionar inceput si vrem sa il continuam
                    JOptionPane.showMessageDialog(null, "Nu exista un test inceput. Incepe unul nou!","Eroare", JOptionPane.CLOSED_OPTION);
                }
            }
        }
    }
}
