import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TipChestionar extends JFrame{
    private IncepeChestionar ic;
    private InterfataCursant iC;
    private String utilizator;
    JButton bCancel,bTipA,bTipB,bTipC,bTipD,bTipTr;
    JLabel lCategorie;
    JPanel p1,p2;
    
    public TipChestionar(String u){
        super("Categorie chestionar");
        utilizator = u;
        AscultatorButoane a = new AscultatorButoane();
        
        lCategorie = new JLabel("Alegeti categoria chestionarului:");
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(a);
        bTipA = new JButton("A");
        bTipA.addActionListener(a);
        bTipB = new JButton("B");
        bTipB.addActionListener(a);
        bTipC = new JButton("C");
        bTipC.addActionListener(a);
        bTipD = new JButton("D");
        bTipD.addActionListener(a);
        bTipTr = new JButton("Tr");
        bTipTr.addActionListener(a);
        
        p1 = new JPanel();
        p1.setLayout(new GridLayout(6,1));
        p1.add(lCategorie);
        p1.add(bTipA);
        p1.add(bTipB);
        p1.add(bTipC);
        p1.add(bTipD);
        p1.add(bTipTr);
        
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
            if(ev.getSource() == bTipA){
                ic = new IncepeChestionar(utilizator,"A");
            }
            if(ev.getSource() == bTipB){
                ic = new IncepeChestionar(utilizator,"B");
            }
            if(ev.getSource() == bTipC){
                ic = new IncepeChestionar(utilizator,"C");
            }
            if(ev.getSource() == bTipD){
                ic = new IncepeChestionar(utilizator,"D");
            }
            if(ev.getSource() == bTipTr){
                ic = new IncepeChestionar(utilizator,"Tr");
            }
            if(ev.getSource() == bCancel){
                iC = new InterfataCursant(utilizator);
            }
            setVisible(false);
        }
    }
}
