import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InterfataAdministrator_Teorie extends JFrame{
    private InterfataAdministrator iA;
    
    private JLabel lA, lB, lC, lIntrebare, lRaspuns;
    private JTextField tfA, tfB, tfC, tfIntrebare;
    private JComboBox cbRaspunsuri;
    private JButton bOK, bCancel;
    private JPanel p1, p2;
    
    public InterfataAdministrator_Teorie(){
        super("Interfata Administrator Teorie");
        Font f = new Font("bold", Font.BOLD, 15);
        AscultatorButoane a = new AscultatorButoane();
        
        lRaspuns = new JLabel("Raspuns:"); lRaspuns.setFont(f);
        lIntrebare = new JLabel("Intrebare:"); lIntrebare.setFont(f);
        lA = new JLabel("Varianta A:"); lA.setFont(f);
        lB = new JLabel("Varianta B:"); lB.setFont(f);
        lC = new JLabel("Varianta C:"); lC.setFont(f);
        
        tfIntrebare = new JTextField(25);
        tfA = new JTextField(25);
        tfB = new JTextField(25);
        tfC = new JTextField(25);
        
        cbRaspunsuri = new JComboBox();
        final String[] sList = {"ABC", "AB", "BC", "AC", "A", "B", "C"};
        for(String x : sList)
            cbRaspunsuri.addItem(x);
        
        bOK = new JButton("OK"); bOK.setPreferredSize(new Dimension(85, 25));
        bOK.addActionListener(a);
        bCancel = new JButton("Cancel"); bCancel.setPreferredSize(new Dimension(85, 25));
        bCancel.addActionListener(a);
             
        p1 = new JPanel();
        p1.setLayout(new GridLayout(5,2));
        p1.add(lIntrebare); p1.add(tfIntrebare);
        p1.add(lA); p1.add(tfA);
        p1.add(lB); p1.add(tfB);
        p1.add(lC); p1.add(tfC);
        p1.add(lRaspuns); p1.add(cbRaspunsuri);
        
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
    
    class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            ListaIntrebari lista = new ListaIntrebari();
            if(ev.getSource() == bOK){
                if(!tfIntrebare.getText().isEmpty()&&tfIntrebare.getText().charAt(0)!=' '&&
                   !tfA.getText().isEmpty()&&tfA.getText().charAt(0)!=' '&&
                   !tfB.getText().isEmpty()&&tfB.getText().charAt(0)!=' '&&
                   !tfC.getText().isEmpty()&&tfC.getText().charAt(0)!=' '){           
                    lista.adaugaIntrebare(tfIntrebare.getText(), tfA.getText(), tfB.getText(), tfC.getText(), cbRaspunsuri.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null, "Intrebare adaugata.");
                }else{
                    JOptionPane.showMessageDialog(null, "Unul sau mai multe spatii nu au fost completate");
                }  
            }
            if(ev.getSource() == bCancel){
                setVisible(false);
                iA = new InterfataAdministrator();
            }
        }
    }
}
