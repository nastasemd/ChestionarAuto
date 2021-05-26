import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfataCursant extends JFrame{
    
    private TipChestionar tc;
    private InterfataCursant_Legislatie iCL;
    private Logare l;
    
    private String utilizator;
    JButton bCancel,bParteTeoretica, bPartePractica;
    JLabel lLegislatie,lTestare;
    JPanel p1,p2;
    
    public InterfataCursant(String u){
        super("Interfata Cursant");
        utilizator = u;
        AscultatorButoane a = new AscultatorButoane();
        
        lLegislatie = new JLabel("Vizualizeaza legislatia:");
        lTestare = new JLabel("Incepe testarea:");
        
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(a);
        bParteTeoretica = new JButton("Legislatie");
        bParteTeoretica.addActionListener(a);
        bPartePractica = new JButton("Testare");
        bPartePractica.addActionListener(a);
        
        p1 = new JPanel();
        p1.setLayout(new GridLayout(2,2));
        p1.add(lLegislatie);
        p1.add(bParteTeoretica);
        p1.add(lTestare);
        p1.add(bPartePractica);
        
        p2 = new JPanel();
        p2.add(bCancel);
        
        getContentPane().add(p1, BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if(ev.getSource() == bPartePractica){
                setVisible(false);
                tc = new TipChestionar(utilizator);
                tc.setSize(300,250);
                
            }else if(ev.getSource() == bParteTeoretica){
                iCL = new InterfataCursant_Legislatie();
                iCL.setSize(500,500);
            }
            else{
                l = new Logare();
                setVisible(false);
            }
        }
    }
}
