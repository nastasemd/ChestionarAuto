import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfataAdministrator extends JFrame{
    private InterfataAdministrator_Teorie iAT;
    private InterfataAdministrator_Cursant iAC;
    private Logare l;
    
    private JButton bTeorie, bCursant, bCancel;
    private JLabel lTeorie, lCursant;
    private JPanel p1, p2;
    
    public InterfataAdministrator(){
        super("Interfata Administrator");
        AscultatorButoane a = new AscultatorButoane();
        
        lTeorie = new JLabel("Adauga intrebare: ");
        lCursant = new JLabel("Administrare cursanti: ");
        
        bTeorie = new JButton("Teorie");
        bTeorie.addActionListener(a);
        bCursant = new JButton("Cursant"); 
        bCursant.addActionListener(a);
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(a);
        
        p1 = new JPanel();
        p1.setLayout(new GridLayout(2,2));
        p1.add(lTeorie); p1.add(bTeorie);
        p1.add(lCursant); p1.add(bCursant);
        
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
            if(ev.getSource() == bTeorie){
                setVisible(false);
                iAT = new InterfataAdministrator_Teorie();
            }
            if(ev.getSource() == bCursant){
                setVisible(false);
                iAC = new InterfataAdministrator_Cursant();
            }
            if(ev.getSource() == bCancel){
                setVisible(false);
                l = new Logare();
            }
        }
    }
}
