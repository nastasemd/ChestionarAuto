import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;

public class ChestionarNou extends JFrame{
    private JPanel p1,p2,p3,p4;
    private JCheckBox bRaspunsA,bRaspunsB,bRaspunsC;
    private JButton bConfirm,bMaiTarziu,bUrmatoareaIntrebare,bRezultate;
    private JLabel lNrIntrebare,lTextRaspunsuriCorecte,lNrRaspunsuriCorecte,
            lTextRaspunsuriGresite,lNrRaspunsuriGresite,lIntrebare,lImagine;
    
    private String utilizator,tip,numeFisier;
    private int indexIntrebare = 0,raspunsuriCorecte = 0,raspunsuriGresite = 0;
    private ListaIntrebari lista;
    private boolean amRaspuns = false;
    private ChestionarTerminat ct;
    private GestorFisiere gf;
    
    private JLabel lCeas;
    private int minut = 30,secunda = 0;
    private java.util.Timer timp = new java.util.Timer();
    private TimerTask x = new TimerTask(){
        public void run(){        
            if(secunda == 0){
                secunda = 59;
                minut--;
                lCeas.setText("Timp ramas:" + minut + ":" + secunda);
            }else{
                secunda--;
                if(secunda < 10)
                    lCeas.setText("Timp ramas:" + minut + ":0" + secunda);
                else
                    lCeas.setText("Timp ramas:" + minut + ":" + secunda);
            }
            if(minut == -1){
                lCeas.setText("Gata");
                timp.cancel();
                JOptionPane.showMessageDialog(null, "Timpul s-a terminat!");
                bRezultate.setVisible(true);//Afisam butonul "Vezi rezultate"
                bConfirm.setEnabled(false);//Dezactivam celelalte butoane
                bMaiTarziu.setEnabled(false);
                bUrmatoareaIntrebare.setEnabled(false);
                bRaspunsA.setEnabled(false);//Nu mai lasam cursantul sa raspunda la intrebari
                bRaspunsB.setEnabled(false);
                bRaspunsC.setEnabled(false);
            }
            lCeas.updateUI();      
        }   
    };
    
    public ChestionarNou(String u,String t){
        super("Chestionar auto categoria " + t);
        utilizator = u;//id-ul utilizatorului
        tip = t;//categoria chestionarului
        LocalDateTime data = LocalDateTime.now();//Data curenta
        numeFisier = "teste/" + utilizator + "_" + tip + "_" + data.getDayOfMonth() + "-" + data.getMonthValue() + "-" 
                + data.getYear() + "_" + data.getHour() + "-" + data.getMinute() + ".txt";//nume fisier nou
        lNrIntrebare = new JLabel("Intrebarea " + (indexIntrebare + 1) + " din 26",SwingConstants.CENTER);//Numar intrebare
        lCeas = new JLabel("Timp ramas:30:00");//Ceas
        
        lTextRaspunsuriCorecte = new JLabel("Raspunsuri corecte: ");//Numar raspunsuri corecte
        lNrRaspunsuriCorecte = new JLabel(Integer.toString(raspunsuriCorecte));
        lNrRaspunsuriCorecte.setForeground(Color.GREEN);
        
        lTextRaspunsuriGresite = new JLabel("Raspunsuri gresite: ");//Numar raspunsuri gresite
        lNrRaspunsuriGresite = new JLabel(Integer.toString(raspunsuriGresite));
        lNrRaspunsuriGresite.setForeground(Color.RED);
        
        lista = new ListaIntrebari();//Lista cu intrebari
        lista.citesteIntrebari();//Citim 26 de intrebari aleator din fisier
        gf = new GestorFisiere(numeFisier);
        gf.copiaza(lista);
        
        lImagine = new JLabel();
        lIntrebare = new JLabel();
        bRaspunsA = new JCheckBox();
        bRaspunsB = new JCheckBox();
        bRaspunsC = new JCheckBox();
        seteazaIntrebare(indexIntrebare);//Afisam prima intrebare
        
        AscultatorButoane ab = new AscultatorButoane();//butoane confirma,mai tarziu,urmatoarea intrebare si vezi rezultate
        bConfirm = new JButton("Confirma raspuns");
        bConfirm.addActionListener(ab);
        bMaiTarziu = new JButton("Raspunde mai tarziu");
        bMaiTarziu.addActionListener(ab);
        bUrmatoareaIntrebare = new JButton("Urmatoarea intrebare");
        bUrmatoareaIntrebare.addActionListener(ab);
        bRezultate = new JButton("Vezi rezultate");
        bRezultate.addActionListener(ab);
        bRezultate.setVisible(false);
        
        p1 = new JPanel();//Panelul de sus cu numarul de intrebari,raspunsuri corecte/gresite si timpul ramas
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        p1.add(lNrIntrebare);
        p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(lCeas);
        p2.add(lTextRaspunsuriCorecte);
        p2.add(lNrRaspunsuriCorecte);
        p2.add(lTextRaspunsuriGresite);
        p2.add(lNrRaspunsuriGresite);
        p1.add(p2);
        getContentPane().add(p1,BorderLayout.NORTH);
        
        p3 = new JPanel();//Panelul din mijloc cu intrebarea propriu zisa si variantele de raspuns
        p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
        p3.add(lImagine);
        p3.add(lIntrebare);
        p3.add(bRaspunsA);
        p3.add(bRaspunsB);
        p3.add(bRaspunsC);
        getContentPane().add(p3,BorderLayout.CENTER);
        
        p4 = new JPanel();//Panelul de jos cu butoanele de confirma si raspunde mai tarziu
        p4.add(bConfirm);
        p4.add(bMaiTarziu);
        p4.add(bUrmatoareaIntrebare);
        p4.add(bRezultate);
        getContentPane().add(p4,BorderLayout.SOUTH);
        
        pack();//Afisare frame complet
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        timp.scheduleAtFixedRate(x, 1000, 1000);//ceas (timer x|intarziere = 1 sec|durata actiune = 1 sec)
    }
    
    public void seteazaIntrebare(int i){//Afisam intrebarea i
        Intrebare intrebareCurenta = lista.getIntrebare(i);
        String s = "imagini/prob" + intrebareCurenta.getNumarIntrebare() + ".jpg";
        File f = new File(s);
        if(f.exists())//Verificam daca intrebarea respectiva are imagine atasata
            lImagine.setIcon(new ImageIcon(s));
        else
            lImagine.setIcon(new ImageIcon(""));
        lIntrebare.setText(" " + intrebareCurenta.getIntrebare());//Actualizam cu intrebarea noua
        bRaspunsA.setText(intrebareCurenta.getRaspunsA());
        bRaspunsB.setText(intrebareCurenta.getRaspunsB());
        bRaspunsC.setText(intrebareCurenta.getRaspunsC());
        lNrIntrebare.setText("Intrebarea " + (i + 1) + " din 26");
    }
    
    public void esteCorect(){//Verificam daca am raspuns corect
        boolean amApasatA = bRaspunsA.isSelected();//Pentru nu avea ziduri de text
        boolean amApasatB = bRaspunsB.isSelected();
        boolean amApasatC = bRaspunsC.isSelected();
        boolean eraCorectA = lista.getIntrebare(indexIntrebare).isCorectA();
        boolean eraCorectB = lista.getIntrebare(indexIntrebare).isCorectB();
        boolean eraCorectC = lista.getIntrebare(indexIntrebare).isCorectC();
        //Coloram fundalul.Rosu = raspuns gresit selectat, verde = raspuns corect selectat,Gri = raspuns corect dar neselectat
        if(amApasatA && eraCorectA)//Daca am ales un raspuns corect
            bRaspunsA.setBackground(Color.GREEN);
        if(amApasatB && eraCorectB)
            bRaspunsB.setBackground(Color.GREEN);
        if(amApasatC && eraCorectC)
            bRaspunsC.setBackground(Color.GREEN);
        if(amApasatA && !eraCorectA)//Daca am ales un raspuns gresit
            bRaspunsA.setBackground(Color.RED);
        if(amApasatB && !eraCorectB)
            bRaspunsB.setBackground(Color.RED);
        if(amApasatC && !eraCorectC)
            bRaspunsC.setBackground(Color.RED);
        if(!amApasatA && eraCorectA)//Daca nu am ales un raspunsu corect
            bRaspunsA.setBackground(Color.GRAY);
        if(!amApasatB && eraCorectB)
            bRaspunsB.setBackground(Color.GRAY);
        if(!amApasatC && eraCorectC)
            bRaspunsC.setBackground(Color.GRAY);
        //Daca am selectat unul din raspunsuri si nu era corect
        if((amApasatA && !eraCorectA) || (amApasatB && !eraCorectB) || (amApasatC && !eraCorectC)){
            raspunsuriGresite++;
            lNrRaspunsuriGresite.setText((Integer.toString(raspunsuriGresite)));
        }
        //Daca nu am selectat un raspuns care era corect
        else if((!amApasatA && eraCorectA) || (!amApasatB && eraCorectB) || (!amApasatC && eraCorectC)){
            raspunsuriGresite++;
            lNrRaspunsuriGresite.setText((Integer.toString(raspunsuriGresite)));
        }
        else{//Altfel am raspuns corect
            raspunsuriCorecte++;
            lNrRaspunsuriCorecte.setText((Integer.toString(raspunsuriCorecte)));
        }
        //Copiem raspunsul dat
        gf.copiaza(indexIntrebare,bRaspunsA.getBackground(),bRaspunsB.getBackground(),bRaspunsC.getBackground());
    }
    
    public void reset(){//Resetam butoanele
        bRaspunsA.setSelected(false);
        bRaspunsB.setSelected(false);
        bRaspunsC.setSelected(false);
        bRaspunsA.setBackground(new JLabel().getBackground());//Resetam fundalurile butoanelor in cel default
        bRaspunsB.setBackground(new JLabel().getBackground());
        bRaspunsC.setBackground(new JLabel().getBackground());
    }
    
    public boolean urmatoareaIntrebare(){
        indexIntrebare++;
        for(int i = indexIntrebare;i < 26;i++)//Verificam daca nu s-a raspuns la toate intrebarile urmatoare
            if(!lista.getIntrebare(i).amRaspuns()){
                indexIntrebare = i;
                return true;
            }
        for(int i = 0;i < 26;i++)//In caz ca am parcurs toate cele 26 de intrebari, o luam iarasi de la capat
            if(!lista.getIntrebare(i).amRaspuns()){
                indexIntrebare = i;
                return true;
            }
        return false;//Daca s-a raspuns la toate intrebarile
    }
    
    private class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == bConfirm){//Daca am apasat pe butonul confirma
                if((bRaspunsA.isSelected() || bRaspunsB.isSelected() || bRaspunsC.isSelected())){
                    if(!amRaspuns){//Daca nu am raspuns deja la intrebare
                    esteCorect();//Verificam daca raspunsurile alese sunt corecte
                    lista.getIntrebare(indexIntrebare).setAmRaspuns();//Spunem ca am raspuns la aceasta intrebare
                    amRaspuns = true;
                    }
                    else{//Daca am raspuns deja la intrebare
                        JOptionPane.showMessageDialog(null, "Ai raspuns deja la intrebare.Treci la urmatoarea intrebare!","Eroare", JOptionPane.CLOSED_OPTION);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Nu ai selectat nici un raspuns!","Eroare", JOptionPane.CLOSED_OPTION);
                }
            }
            if(e.getSource() == bMaiTarziu){//Daca am apasat pe butonul raspunde mai tarziu trecem la urmatoare intrebare
                if(!amRaspuns){//Daca nu am raspuns la intrebare putem trece la alta intrebare
                    if(urmatoareaIntrebare()){
                        seteazaIntrebare(indexIntrebare);
                        validate();
                        reset();
                    }
                }
                else{//Daca am raspuns la intrebare nu putem sari peste ea
                    JOptionPane.showMessageDialog(null, "Ai raspuns deja la intrebare.Treci la urmatoarea intrebare!","Eroare", JOptionPane.CLOSED_OPTION);
                }
                
            }
            if(e.getSource() == bUrmatoareaIntrebare){
                if(amRaspuns){//Daca am raspuns la intrebare
                    if(urmatoareaIntrebare() && raspunsuriGresite < 5){//Daca exista intrebare la care nu am raspuns
                        reset();
                        seteazaIntrebare(indexIntrebare);
                        validate();
                        amRaspuns = false;
                    }
                    else if(!urmatoareaIntrebare()){//Daca a fost ultima intrebare
                        gf.copiaza(raspunsuriCorecte,raspunsuriGresite);//Nr raspunsuri corecte/gresite
                        bRezultate.setVisible(true);//Afisam butonul "Vezi rezultate"
                        if(raspunsuriCorecte >= 22) //Daca am raspuns corect la 22 de intrebari => Am trecut testul
                            JOptionPane.showMessageDialog(null, "Testul s-a incheiat!\nAti raspunsul corect la " + raspunsuriCorecte + " intrebari.\n"
                                    +"Ati promovat cu success!","Test incheiat", JOptionPane.CLOSED_OPTION);
                        else //Daca am raspuns la mai putin de 22 => Nu am trecut testul
                            JOptionPane.showMessageDialog(null, "Testul s-a incheiat!\nAti raspunsul corect la " + raspunsuriCorecte + " intrebari.\n"
                                    +"Din pacate nu ati promovat.","Test incheiat", JOptionPane.CLOSED_OPTION);
                    }
                    if(raspunsuriGresite > 4){ //Daca am raspuns gresit la 4 de intrebari => Nu am trecut testul
                        JOptionPane.showMessageDialog(null, "Testul s-a incheiat!\nAti raspunsul gresit la " + raspunsuriGresite + " intrebari.\n"
                                +"Din pacate nu ati promovat!","Test incheiat", JOptionPane.CLOSED_OPTION);
                        bRezultate.setVisible(true);//Afisam butonul "Vezi rezultate"
                        bConfirm.setEnabled(false);
                        bMaiTarziu.setEnabled(false);
                        bUrmatoareaIntrebare.setEnabled(false);
                        bRaspunsA.setEnabled(false);//Nu mai lasam cursantul sa raspunda la intrebari
                        bRaspunsB.setEnabled(false);
                        bRaspunsC.setEnabled(false);
                        gf.copiaza("Nepromovat");//"Nepromovat"
                        gf.copiaza(raspunsuriCorecte,raspunsuriGresite);//Nr raspunsuri corecte/gresite
                    }
                }
                else{//Daca nu am raspuns la intrebare
                    JOptionPane.showMessageDialog(null, "Nu ai raspuns la intrebare!","Eroare", JOptionPane.CLOSED_OPTION);
                }
            }
            if(e.getSource() == bRezultate){
                setVisible(false);
                ct = new ChestionarTerminat(numeFisier,tip);//Cream un chestionar in care putem doar sa vedem rezultatele
                ct.setSize(1000,400);
            }
        }
    }
}
