import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChestionarNeterminat extends JFrame{
    private JPanel p1,p2,p3,p4;
    private JCheckBox bRaspunsA,bRaspunsB,bRaspunsC;
    private JButton bConfirm,bMaiTarziu,bUrmatoareaIntrebare,bRezultate;
    private JLabel lNrIntrebare,lTextRaspunsuriCorecte,lNrRaspunsuriCorecte,
            lTextRaspunsuriGresite,lNrRaspunsuriGresite,lIntrebare,lImagine;
    
    private ChestionarTerminat ct;
    private String numeFisier,tip;
    private int indexIntrebare = 0,raspunsuriCorecte = 0,raspunsuriGresite = 0;
    private GestorFisiere gf;
    private ListaIntrebari lista;
    private boolean amRaspuns = false;
    
    public ChestionarNeterminat(String numeFisier,String t){
        super("Chestionar auto categoria " + t);
        this.numeFisier = numeFisier;
        tip = t;
        lista = new ListaIntrebari();
        lista.citesteIntrebari(numeFisier);//Citim intrebarile
        gf = new GestorFisiere(numeFisier);
        gf.citesteRaspunsuriPartiale();//Citim raspunsurile date deja
        calculeazaRaspunsuri();//Calculam nr de raspunsuri corecte si gresite
        
        lNrIntrebare = new JLabel("Intrebarea " + (indexIntrebare + 1) + " din 26",SwingConstants.CENTER);
        lTextRaspunsuriCorecte = new JLabel("Raspunsuri corecte: ");
        lNrRaspunsuriCorecte = new JLabel(Integer.toString(raspunsuriCorecte));
        lNrRaspunsuriCorecte.setForeground(Color.GREEN);
        lTextRaspunsuriGresite = new JLabel("Raspunsuri gresite: ");
        lNrRaspunsuriGresite = new JLabel(Integer.toString(raspunsuriGresite));
        lNrRaspunsuriGresite.setForeground(Color.RED);
        
        lImagine = new JLabel();
        lIntrebare = new JLabel();
        bRaspunsA = new JCheckBox();
        bRaspunsB = new JCheckBox();
        bRaspunsC = new JCheckBox();
        seteazaStart();//Seteaza prima intrebare la care nu s-a raspuns
        
        AscultatorButoane ab = new AscultatorButoane();
        bConfirm = new JButton("Confirma raspuns");
        bConfirm.addActionListener(ab);
        bMaiTarziu = new JButton("Raspunde mai tarziu");
        bMaiTarziu.addActionListener(ab);
        bUrmatoareaIntrebare = new JButton("Urmatoarea intrebare");
        bUrmatoareaIntrebare.addActionListener(ab);
        bRezultate = new JButton("Vezi rezultate");
        bRezultate.addActionListener(ab);
        bRezultate.setVisible(false);
        
        p1 = new JPanel();//Panelul de sus cu numarul de intrebari,raspunsuri corecte/gresite
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        p1.add(lNrIntrebare);
        p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(lTextRaspunsuriCorecte);
        p2.add(lNrRaspunsuriCorecte);
        p2.add(lTextRaspunsuriGresite);
        p2.add(lNrRaspunsuriGresite);
        p1.add(p2);
        getContentPane().add(p1,BorderLayout.NORTH);
        
        p3 = new JPanel();//Panelul din mijloc cu intrebarea propriu zisa
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
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void calculeazaRaspunsuri(){
        for(int i = 0;i < 26;i++){
            //Daca nu toate au fundal default(i.e daca s-a raspuns la intrebare)
            if(!(gf.getCuloareA(i).equals("D") && gf.getCuloareB(i).equals("D") && gf.getCuloareC(i).equals("D"))){
 
                lista.getIntrebare(i).setAmRaspuns();
                boolean amRaspunsAGresit = (gf.getCuloareA(i).equals("R") || gf.getCuloareA(i).equals("G"));
                boolean amRaspunsBGresit = (gf.getCuloareB(i).equals("R") || gf.getCuloareB(i).equals("G"));
                boolean amRaspunsCGresit = (gf.getCuloareC(i).equals("R") || gf.getCuloareC(i).equals("G"));
                if(amRaspunsAGresit || amRaspunsBGresit || amRaspunsCGresit)
                    raspunsuriGresite++;
                else
                    raspunsuriCorecte++;
            }
        }
    }
    
    public void seteazaStart(){//Vedem care este prima intrebare la care nu s-a raspuns
        for(int i = 0; i < 26;i++)
            if(!lista.getIntrebare(i).amRaspuns()){
                indexIntrebare = i;
                seteazaIntrebare(indexIntrebare);
                break;
            }
        //Setam nr de raspunsuri gresite si corecte
        lNrRaspunsuriGresite.setText((Integer.toString(raspunsuriGresite)));
        lNrRaspunsuriCorecte.setText((Integer.toString(raspunsuriCorecte)));
    }
    
    public void esteCorect(){//Verificam daca raspunsul este corect
        boolean amApasatA = bRaspunsA.isSelected();//Pentru nu avea ziduri de text
        boolean amApasatB = bRaspunsB.isSelected();
        boolean amApasatC = bRaspunsC.isSelected();
        boolean eraCorectA = lista.getIntrebare(indexIntrebare).isCorectA();
        boolean eraCorectB = lista.getIntrebare(indexIntrebare).isCorectB();
        boolean eraCorectC = lista.getIntrebare(indexIntrebare).isCorectC();
        //Coloram fundalul.Rosu = raspuns gresit selectat, verde = raspuns corect selectat, Gri = raspuns corect dar neselectat
        //Daca am ales raspunsul gresit
        if(amApasatA && !eraCorectA)
            bRaspunsA.setBackground(Color.RED);
        if(amApasatB && !eraCorectB)
            bRaspunsB.setBackground(Color.RED);
        if(amApasatC && !eraCorectC)
            bRaspunsC.setBackground(Color.RED);
        //Daca nu am ales raspunsul corect
        if(!amApasatA && eraCorectA)
            bRaspunsA.setBackground(Color.GRAY);
        if(!amApasatB && eraCorectB)
            bRaspunsB.setBackground(Color.GRAY);
        if(!amApasatC && eraCorectC)
            bRaspunsC.setBackground(Color.GRAY);
        //Daca am ales raspunsul corect
        if(amApasatA && eraCorectA)
            bRaspunsA.setBackground(Color.GREEN);
        if(amApasatB && eraCorectB)
            bRaspunsB.setBackground(Color.GREEN);
        if(amApasatC && eraCorectC)
            bRaspunsC.setBackground(Color.GREEN);
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
        else{//Raspuns corect
            raspunsuriCorecte++;
            lNrRaspunsuriCorecte.setText((Integer.toString(raspunsuriCorecte)));
        }
        //Copiaza in fisier raspunusl dat
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
    
    public void seteazaIntrebare(int i){//Afisam intrebarea i
        Intrebare intrebareCurenta = lista.getIntrebare(i);
        String s = "imagini/prob" + intrebareCurenta.getNumarIntrebare() + ".jpg";
        File f = new File(s);
        if(f.exists())//Verificam daca intrebarea are imagine atasata
            lImagine.setIcon(new ImageIcon(s));
        else
            lImagine.setIcon(new ImageIcon(""));
        lIntrebare.setText(" " + intrebareCurenta.getIntrebare()); //Actualizam cu intrebarea noua
        bRaspunsA.setText(intrebareCurenta.getRaspunsA());
        bRaspunsB.setText(intrebareCurenta.getRaspunsB());
        bRaspunsC.setText(intrebareCurenta.getRaspunsC());
        lNrIntrebare.setText("Intrebarea " + (indexIntrebare + 1) + " din 26");
    }
    
    public boolean urmatoareaIntrebare(){
        indexIntrebare++;
        for(int i = indexIntrebare;i < 26;i++)//Verificam daca nu s-a raspuns toate intrebarile urmatoare
            if(!lista.getIntrebare(i).amRaspuns()){
                indexIntrebare = i;
                return true;
            }
        for(int i = 0;i < 26;i++)//In caz ca am parcurs toate cele 26 de intrebari, o luam iarasi de la capat
            if(!lista.getIntrebare(i).amRaspuns()){
                indexIntrebare = i;
                return true;
            }
        return false;//Daca s-a raspuns la toate
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
