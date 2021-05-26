import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChestionarTerminat extends JFrame{
    private JPanel p1,p2,p3,p4;
    private JCheckBox bRaspunsA,bRaspunsB,bRaspunsC;
    private JButton bUrmatoareaIntrebare,bIntrebareaAnterioara;
    private JLabel lNrIntrebare,lTextRaspunsuriCorecte,lNrRaspunsuriCorecte,
            lTextRaspunsuriGresite,lNrRaspunsuriGresite,lIntrebare,lImagine,lStatus;
    private String numeFisier,tip;
    
    private int indexIntrebare = 0,raspunsuriCorecte = 0,raspunsuriGresite = 0;
    private GestorFisiere gf;
    private ListaIntrebari lista;
    
    public ChestionarTerminat(String numeFisier,String t){
        super("Rezultate examen auto categoria " + t);
        this.numeFisier = numeFisier;
        tip = t;
        lista = new ListaIntrebari();
        lista.citesteIntrebari(numeFisier);//Citim intrebarile
        gf = new GestorFisiere(numeFisier);
        
        lNrIntrebare = new JLabel("Intrebarea " + (indexIntrebare + 1) + " din 26",SwingConstants.CENTER);
        lStatus = new JLabel();
        seteazaStatus();
                
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
        seteazaIntrebare(indexIntrebare);//Afisam prima intrebare
        
        AscultatorButoane ab = new AscultatorButoane();
        bIntrebareaAnterioara = new JButton("Intrebarea anterioara");
        bIntrebareaAnterioara.addActionListener(ab);
        bUrmatoareaIntrebare = new JButton("Urmatoarea intrebare");
        bUrmatoareaIntrebare.addActionListener(ab);
        
        p1 = new JPanel();//Panelul de sus cu numarul intrebarii,raspunsuri corecte/gresite si daca a promovat
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        p1.add(lNrIntrebare);
        p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(lTextRaspunsuriCorecte);
        p2.add(lNrRaspunsuriCorecte);
        p2.add(lTextRaspunsuriGresite);
        p2.add(lNrRaspunsuriGresite);
        p2.add(lStatus);
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
        p4.add(bIntrebareaAnterioara);
        p4.add(bUrmatoareaIntrebare);
        getContentPane().add(p4,BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reset();
    }
        
    public void seteazaStatus(){//Aratam daca a promovat sau nu
        String s = gf.citesteRaspunsuri();
        String[] s1 = s.split(" ");
        if(s1.length == 3){
            if(s1[0].equals("Nepromovat"))
                lStatus.setText("Nepromovat");
            raspunsuriCorecte = Integer.parseInt(s1[1]);
            raspunsuriGresite = Integer.parseInt(s1[2]);
        }
        else{
            raspunsuriCorecte = Integer.parseInt(s1[0]);
            raspunsuriGresite = Integer.parseInt(s1[1]);
            if(raspunsuriCorecte >= 22)
                lStatus.setText("Promovat");
            else
                lStatus.setText("Nepromovat");
        }
    }
    
    public void reset(){
        //Pentru butonul A
        if(gf.getCuloareA(indexIntrebare).equals("R"))
            bRaspunsA.setBackground(Color.RED);
        else if(gf.getCuloareA(indexIntrebare).equals("V"))
            bRaspunsA.setBackground(Color.GREEN);
        else if(gf.getCuloareA(indexIntrebare).equals("G"))
            bRaspunsA.setBackground(Color.GRAY);
        else
            bRaspunsA.setBackground(new JLabel().getBackground());
        //Pentru butonul B
        if(gf.getCuloareB(indexIntrebare).equals("R"))
            bRaspunsB.setBackground(Color.RED);
        else if(gf.getCuloareB(indexIntrebare).equals("V"))
            bRaspunsB.setBackground(Color.GREEN);
        else if(gf.getCuloareB(indexIntrebare).equals("G"))
            bRaspunsB.setBackground(Color.GRAY);
        else
            bRaspunsB.setBackground(new JLabel().getBackground());
        //Pentru butonul C
        if(gf.getCuloareC(indexIntrebare).equals("R"))
            bRaspunsC.setBackground(Color.RED);
        else if(gf.getCuloareC(indexIntrebare).equals("V"))
            bRaspunsC.setBackground(Color.GREEN);
        else if(gf.getCuloareC(indexIntrebare).equals("G"))
            bRaspunsC.setBackground(Color.GRAY);
        else
            bRaspunsC.setBackground(new JLabel().getBackground());
    }
    
    public void seteazaIntrebare(int i){//Afisam intrebarea i
        Intrebare intrebareCurenta = lista.getIntrebare(i);
        String s = "imagini/prob" + intrebareCurenta.getNumarIntrebare() + ".jpg";
        File f = new File(s);
        if(f.exists())//Daca intrebarea are imagine atasata
            lImagine.setIcon(new ImageIcon(s));
        else
            lImagine.setIcon(new ImageIcon(""));
        lIntrebare.setText(" " + intrebareCurenta.getIntrebare());//Actualizam cu intrebarea noua
        bRaspunsA.setText(intrebareCurenta.getRaspunsA());
        bRaspunsB.setText(intrebareCurenta.getRaspunsB());
        bRaspunsC.setText(intrebareCurenta.getRaspunsC());
        lNrIntrebare.setText("Intrebarea " + (indexIntrebare + 1) + " din 26");
    }
    
    private class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == bUrmatoareaIntrebare){
                    if((indexIntrebare + 1) > 25)
                        indexIntrebare = 0;
                    else
                        indexIntrebare++;
                    reset();
                    seteazaIntrebare(indexIntrebare);
                    validate();
                }
            if(e.getSource() == bIntrebareaAnterioara){
                    if((indexIntrebare - 1) < 0)
                        indexIntrebare = 25;
                    else
                        indexIntrebare--;
                    reset();
                    seteazaIntrebare(indexIntrebare);
                    validate();
                }
            }
        }
    
}
