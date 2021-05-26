import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfataCursant_Inregistrare extends JFrame{
    private Logare l;   
    private ListaCursanti_Conturi lista = new ListaCursanti_Conturi();
    
    Cursant persoana=new Cursant();
    JLabel lID, lParola, lCategorie, lNume, lPrenume;
    JButton bOK, bCancel;
    JComboBox CBCategorie;
    JTextField TFID, TFNume, TFPrenume;
    JPasswordField PWFParola;
    JPanel p1, p2;
    
    public InterfataCursant_Inregistrare(){
        super("Inregistrare");
        Font f = new Font("bold", Font.BOLD, 15);
        AsculatatorButoane a = new AsculatatorButoane();
        FocusText j = new FocusText();
        
        lID = new JLabel("ID(minim 4): "); lID.setFont(f);
        lParola = new JLabel("Parola(minim 8): "); lParola.setFont(f);
        lCategorie = new JLabel("Categorie: "); lCategorie.setFont(f);
        lNume = new JLabel("Nume: "); lNume.setFont(f);
        lPrenume = new JLabel("Prenume: "); lPrenume.setFont(f);
        
        bOK = new JButton("OK");
        bOK.addActionListener(a);
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(a);
        
        CBCategorie = new JComboBox();
        final String[] sList = {"A", "B", "C", "D", "Tr"};
        for(String x:sList)
            CBCategorie.addItem(x);
        
        TFID = new JTextField("Completati campul!", 15);
        TFID.addFocusListener(j);
        TFNume = new JTextField("Completati campul!", 15);
        TFNume.addFocusListener(j);
        TFPrenume = new JTextField("Completati campul!", 15);
        TFPrenume.addFocusListener(j);
        
        PWFParola = new JPasswordField("********", 15);
        PWFParola.addFocusListener(j);
        
        p1 = new JPanel();
        p1.setLayout(new GridLayout(5,2));
        p1.add(lNume); p1.add(TFNume);
        p1.add(lPrenume);p1.add(TFPrenume);
        p1.add(lCategorie); p1.add(CBCategorie);
        p1.add(lID); p1.add(TFID);
        p1.add(lParola); p1.add(PWFParola);
        
        p2 = new JPanel();
        p2.add(bOK);
        p2.add(bCancel);
        
        getContentPane().add(p1, BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    class AsculatatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if(ev.getSource() == bOK){
                if((TFID.getText().isEmpty() || TFID.getText().equals("Completati campul!")) || 
                   (TFNume.getText().isEmpty() || TFNume.getText().equals("Completati campul!")) ||
                   (TFPrenume.getText().isEmpty() || TFPrenume.getText().equals("Completati campul!")) ||
                   (PWFParola.getText().isEmpty() || PWFParola.getText().equals("********")))
                    JOptionPane.showMessageDialog(null, "Completati toate campurile!");
                
                else if(TFID.getText().length()<4)
                    JOptionPane.showMessageDialog(null, "ID-ul trebuie sa fie format din cel putin 4 simboluri!");
                
                else if(lista.verificareID(TFID.getText()))
                    JOptionPane.showMessageDialog(null, "Numele de utilizator exsita. Introduceti un alt ID!");

                else if(PWFParola.getText().length()<8)
                    JOptionPane.showMessageDialog(null, "Parola trebuie sa fie formata din cel putin 8 simboluri!");
                
                else{
                    Cursant c = new Cursant(TFNume.getText(), TFPrenume.getText(), TFID.getText(), PWFParola.getText(), (String)CBCategorie.getSelectedItem());
                    lista.adaugaCursant(c);
                    lista.adaugaID(c);
                    lista.scriereFisiere();
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Cont adaugat.");
                    l = new Logare();
                }       
            }
            if(ev.getSource() == bCancel){
                setVisible(false);
                l = new Logare();
            }
        }
    }
    
    class FocusText implements FocusListener{
        public void focusGained(FocusEvent ev){
            if(ev.getSource() == TFID)
                TFID.setText("");
            if(ev.getSource() == PWFParola)
                PWFParola.setText("");
            if(ev.getSource() == TFNume)
                TFNume.setText("");
            if(ev.getSource() == TFPrenume)
                TFPrenume.setText("");
        }
        public void focusLost(FocusEvent ev){
            if(ev.getSource() == TFID && TFID.getText().equals(""))
                TFID.setText("Completati campul!");
            if(ev.getSource() == TFNume && TFNume.getText().equals(""))
                TFNume.setText("Completati campul!");
            if(ev.getSource() == TFPrenume && TFPrenume.getText().equals(""))
                TFPrenume.setText("Completati campul!");
            if(ev.getSource() == PWFParola && PWFParola.getText().equals(""))
                PWFParola.setText("********");
        }
    }
}