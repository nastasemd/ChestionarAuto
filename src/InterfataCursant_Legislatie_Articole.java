import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.text.*;

public class InterfataCursant_Legislatie_Articole extends JFrame{
    private JTextArea tpArticol;
    private JScrollPane sb;
    private JButton bPrint;
    private JPanel p;
    
    public InterfataCursant_Legislatie_Articole(String cale){
        super("Legislatie");
        AscultatorButoane a = new AscultatorButoane();
        bPrint = new JButton("Print");
        bPrint.setSize(100, 100);
        bPrint.addActionListener(a);
        tpArticol = new JTextArea();
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(cale));
            String linie;
            while((linie = reader.readLine()) != null){
                tpArticol.append(linie+"\n"); 
            }
            reader.close();
        }catch(IOException ioe){ioe.printStackTrace();}

        tpArticol.setEditable(false);
        tpArticol.setVisible(true);
        tpArticol.setLineWrap(true);
        tpArticol.setWrapStyleWord(true);
        DefaultCaret caret = (DefaultCaret)tpArticol.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        p = new JPanel();
        p.add(bPrint);
        
        sb = new JScrollPane(tpArticol);
        getContentPane().add(p, BorderLayout.EAST);
        getContentPane().add(sb, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if(ev.getSource() == bPrint){      
                try {
                    boolean print = tpArticol.print();
                    if(print)
                        JOptionPane.showMessageDialog(null, "Printrae finalizata.");
                    else
                        JOptionPane.showMessageDialog(null, "Printare nefinalizata.");
                }catch (PrinterException ex) {
                    Logger.getLogger(InterfataCursant_Legislatie_Articole.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }
}
