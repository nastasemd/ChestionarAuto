import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class InterfataAdministrator_Cursant_Teste extends JFrame{
    
    private InterfataAdministrator_Cursant iAC;
    private ChestionarTerminat ct;
    
    private File fisier = new File("teste");
    private File[] listaFisiere;
    private JTable tabel;
    private JScrollPane scrollPane;
    private JButton bCancel, bShow;
    private JPanel p1, p2;
    
    public InterfataAdministrator_Cursant_Teste(){
        super("Teste");
        AscultatorButoane a = new AscultatorButoane();
        listaFisiere = fisier.listFiles();
        String[] coloane = {"ID","Categorie", "Data", "Ora", "Punctaj", " Promovare"};
        String[][] linii = new String[listaFisiere.length][coloane.length];
        
        if(!fisier.isDirectory())
            System.out.println("Nu s-a gasit directorul \"teste\"");
        else{               
            for(int i = 0; i < listaFisiere.length; i++){
                String numeFisier = listaFisiere[i].getName();
                String[] elemente = numeFisier.split("_");
                linii[i][0] = elemente[0];
                linii[i][1] = elemente[1];
                linii[i][2] = elemente[2];
                String[] ora = elemente[3].split("-");
                String[] extensieTxt = ora[1].split("\\.");
                linii[i][3] = ora[0] + ":" + extensieTxt[0];
                String status = chestionarPunctaj(listaFisiere[i].getPath());
                String[] puncte = status.split(" ");
                linii[i][4] = puncte[1];
                linii[i][5] = puncte[0];
                System.out.println(listaFisiere[i].getPath());
            }
        }
        
        tabel = new JTable(linii, coloane);
        scrollPane = new JScrollPane(tabel);
        tabel.setFillsViewportHeight(true);
        
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(a);
        bShow = new JButton("Show");
        bShow.addActionListener(a);
        
        p1 = new JPanel();
        p1.add(scrollPane);
        
        p2 = new JPanel();
        p2.add(bShow);
        p2.add(bCancel);
        
        getContentPane().add(p1, BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }
    
    public String chestionarPunctaj(String fisier){
        int sfarsit = 0;
        String linie = "";
        try{
            BufferedReader in = new BufferedReader(new FileReader(fisier));
            int i = 0;
            while ((linie=in.readLine()) != null){
                i++;
                if(linie.equals("Nepromovat")){
                    sfarsit = 1;
                    linie = in.readLine();
                    break;
                }
                if(i == 28){
                    String[] punctaj = linie.split(" ");
                    int puncte = Integer.parseInt(punctaj[0]);
                    if(puncte >= 22)
                        return "Promovat " + puncte;
                    else
                        return "Nepromovat " + puncte;
                    
                }
            }
            in.close();                   
        }catch(IOException ioe){ioe.printStackTrace();}
        if(sfarsit == 1){
            String[] punctaj = linie.split(" ");
            return "Nepromovat " + punctaj[0];
        }
        return "Nefinalizat " + "0";  
    }
    
    class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if(ev.getSource() == bCancel){
                setVisible(false);
                iAC = new InterfataAdministrator_Cursant();
            }
            if(ev.getSource() == bShow){
                if(tabel.getSelectedRow() != -1){
                    String[] x = tabel.getValueAt(tabel.getSelectedRow(), 3).toString().split(":");
                    String y = x[0]+"-"+x[1];
                    String z = tabel.getValueAt(tabel.getSelectedRow(), 0).toString()+"_"+
                               tabel.getValueAt(tabel.getSelectedRow(), 1).toString()+"_"+
                               tabel.getValueAt(tabel.getSelectedRow(), 2).toString()+"_"+
                               y+".txt";
                    String t = tabel.getValueAt(tabel.getSelectedRow(), 1).toString();
                    if(tabel.getValueAt(tabel.getSelectedRow(), 5).toString().equals("Nefinalizat")){
                        JOptionPane.showMessageDialog(null, "Testul nu a fost finalizat");
                    }else{
                        ct = new ChestionarTerminat("teste\\"+z,tabel.getValueAt(tabel.getSelectedRow(), 1).toString());
                        ct.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        ct.setSize(1000,400);
                    }                       
                }
            }
        }
    }
}
