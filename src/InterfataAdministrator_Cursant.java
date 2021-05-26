import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfataAdministrator_Cursant extends JFrame{
    
    private InterfataAdministrator_Cursant_Intrebari i1;
    private InterfataAdministrator  i2;
    private InterfataAdministrator_Cursant_Teste i3;
    private InterfataAdministrator_Cursant i4;
    private InterfataAdministrator_Cursant i5;
    
    private JLabel lCautare;
    private JButton bCauta, bModifica, bSterge, bAll, bCancel, bTeste, bIntrebari;
    private JTable tCursanti;
    private JTextField tfCursanti;
    private JScrollPane scrollPane;
    private JPanel p1, p2, p3;
    
    public InterfataAdministrator_Cursant(){
        super("Interfata Administrator Cursanti");
        ListaCursanti_Conturi lista = new ListaCursanti_Conturi();
        Font f = new Font("bold", Font.BOLD, 15);
        AscultatorButoane a =new AscultatorButoane();
        
        lCautare = new JLabel("Cursant: "); lCautare.setFont(f);
        
        bCauta = new JButton("Cauta");
        bCauta.addActionListener(a);
        bModifica = new JButton("Modifica");
        bModifica.addActionListener(a);
        bSterge = new JButton("Sterge");
        bSterge.addActionListener(a);
        bAll = new JButton("Show all");
        bAll.addActionListener(a);
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(a);
        bTeste = new JButton("Teste");
        bTeste.addActionListener(a);
        bIntrebari = new JButton("Intrebari");
        bIntrebari.addActionListener(a);
        
        tfCursanti = new JTextField(20);
        
        final String[] column = {"Index", "Nume", "Prenume", "ID", "Parola", "Categorie"};
        String[][] date = lista.cautaCursant("");
        tCursanti = new JTable(date, column);
        scrollPane = new JScrollPane(tCursanti);
        tCursanti.setFillsViewportHeight(true);

        p1 = new JPanel();
        p1.add(lCautare);
        p1.add(tfCursanti);
        p1.add(bCauta);
        p1.add(bAll);
        
        p3 = new JPanel();
        p3.add(scrollPane);
        
        p2 = new JPanel();
        p2.add(bIntrebari);
        p2.add(bTeste);
        p2.add(bModifica);
        p2.add(bSterge);
        p2.add(bCancel);

        getContentPane().add(p1, BorderLayout.NORTH);
        getContentPane().add(p3, BorderLayout.CENTER);
        getContentPane().add(p2, BorderLayout.SOUTH);
      
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public InterfataAdministrator_Cursant(String[][] date){
        super("Interfata Administrator");
        Font f = new Font("bold", Font.BOLD, 15);
        AscultatorButoane a =new AscultatorButoane();
    
        lCautare = new JLabel("Cursant: "); lCautare.setFont(f);
        
        bCauta = new JButton("Cauta");
        bCauta.addActionListener(a);
        bModifica = new JButton("Modifica");
        bModifica.addActionListener(a);
        bSterge = new JButton("Sterge");
        bSterge.addActionListener(a);
        bAll = new JButton("Show all");
        bAll.addActionListener(a);
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(a);
        bTeste = new JButton("Teste");
        bTeste.addActionListener(a);
        bIntrebari = new JButton("Intrebari");
        bIntrebari.addActionListener(a);
        
        tfCursanti = new JTextField(20);
        
        final String[] column = {"Index", "Nume", "Prenume", "ID", "Parola", "Categorie"};
        tCursanti = new JTable(date, column);
        scrollPane = new JScrollPane(tCursanti);
        tCursanti.setFillsViewportHeight(true);

        p1 = new JPanel();
        p1.add(lCautare);
        p1.add(tfCursanti);
        p1.add(bCauta);
        p1.add(bAll);
        
        p3 = new JPanel();
        p3.add(scrollPane);
        
        p2 = new JPanel();
        p2.add(bIntrebari);
        p2.add(bTeste);
        p2.add(bModifica);
        p2.add(bSterge);
        p2.add(bCancel);

        getContentPane().add(p1, BorderLayout.NORTH);
        getContentPane().add(p3, BorderLayout.CENTER);
        getContentPane().add(p2, BorderLayout.SOUTH);
      
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            ListaCursanti_Conturi lista = new ListaCursanti_Conturi();
            if(ev.getSource() == bCauta)
                if(!tfCursanti.getText().isEmpty()){                    
                    String[][] date = lista.cautaCursant(tfCursanti.getText());
                    setVisible(false);
                    i5 = new InterfataAdministrator_Cursant(date);
                }else
                    JOptionPane.showMessageDialog(null, "Indtroduceti un id");
            if(ev.getSource() == bModifica){
                if(tCursanti.getSelectedRow() != -1){
                    lista.modificaCursant(tCursanti.getValueAt(tCursanti.getSelectedRow(), 1).toString(), 
                                          tCursanti.getValueAt(tCursanti.getSelectedRow(), 2).toString(), 
                                          tCursanti.getValueAt(tCursanti.getSelectedRow(), 3).toString(), 
                                          tCursanti.getValueAt(tCursanti.getSelectedRow(), 4).toString(), 
                                          tCursanti.getValueAt(tCursanti.getSelectedRow(), 5).toString(), 
                                          tCursanti.getValueAt(tCursanti.getSelectedRow(), 0).toString());
                    JOptionPane.showMessageDialog(null, "S-a modificat cursantul");
                }else
                    JOptionPane.showMessageDialog(null, "Selectati o linie");
            }
            if(ev.getSource() == bAll){
                setVisible(false);
                i4 = new InterfataAdministrator_Cursant();
            }
            if(ev.getSource() == bSterge){
                if(tCursanti.getSelectedRow() != -1){
                    lista.stergeCursant(tCursanti.getValueAt(tCursanti.getSelectedRow(), 0).toString());
                    JOptionPane.showMessageDialog(null, "A fost sters cursantul");
                }else
                    JOptionPane.showMessageDialog(null, "Selectati o linie");
            }
            if(ev.getSource() == bTeste){
                setVisible(false);
                i3 = new InterfataAdministrator_Cursant_Teste(); 
            }
            if(ev.getSource() == bCancel){
                setVisible(false);
                i2= new InterfataAdministrator();
            }
            if(ev.getSource() == bIntrebari){
                i1 = new InterfataAdministrator_Cursant_Intrebari();
            }
        }  
    }
}
