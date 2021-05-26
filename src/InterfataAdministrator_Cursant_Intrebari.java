import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

public class InterfataAdministrator_Cursant_Intrebari extends JFrame{
    private JTextArea tpIntrebari;
    private JScrollPane sb;
    
    public InterfataAdministrator_Cursant_Intrebari(){
        super("Teorie");
        tpIntrebari = new JTextArea();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("intrebari.txt"));
            String linie;
            int i = 1;
            int nrIntrebare = 0;
            String A = "A: ";
            String B = "B: ";
            String C = "C: ";
            String R = "R: ";
            while((linie = reader.readLine()) != null){
                if(i == 6)
                    i = 1;
                switch(i){
                    case 1: ++nrIntrebare;
                            tpIntrebari.append(Integer.toString(nrIntrebare)+". ");
                            break;
                    case 2: tpIntrebari.append("\t"+A); break;
                    case 3: tpIntrebari.append("\t"+B); break;
                    case 4: tpIntrebari.append("\t"+C); break;
                    case 5: tpIntrebari.append("\t"+R); break;
                }
                i++;
                tpIntrebari.append(linie+"\n"); 
            }
            reader.close();
        }catch(IOException ioe){ioe.printStackTrace();}

        tpIntrebari.setEditable(false);
        tpIntrebari.setVisible(true);
        DefaultCaret caret = (DefaultCaret)tpIntrebari.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        sb = new JScrollPane(tpIntrebari);
                
        getContentPane().add(sb, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
