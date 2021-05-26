import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class InterfataCursant_Legislatie extends JFrame{
    private InterfataCursant_Legislatie_Articole iCLA;
    
    private final String ct1 = "legislatie\\1.txt";
    private final String ct2_1 = "legislatie\\2_1.txt";
    private final String ct2_2 = "legislatie\\2_1.txt";
    private final String ct3_1 = "legislatie\\3_1.txt";
    private final String ct3_2 = "legislatie\\3_2.txt";
    private final String ct4 = "legislatie\\4.txt";
    private final String ct5_1 = "legislatie\\5_1.txt";
    private final String ct5_2 = "legislatie\\5_2.txt";
    private final String ct5_3 = "legislatie\\5_3.txt";
    private final String ct5_4 = "legislatie\\5_4.txt";
    private final String ct5_5 = "legislatie\\5_5.txt";
    private final String ct5_6 = "legislatie\\5_6.txt";
    private final String ct6 = "legislatie\\6.txt";
    private final String ct7 = "legislatie\\7.txt";
    private final String ct8 = "legislatie\\8.txt";
    private final String ct9 = "legislatie\\9.txt";
    private final String ct10 = "legislatie\\10.txt";
    private final JTree tree;
    DefaultMutableTreeNode tLegislatie = new DefaultMutableTreeNode("Legislatie");
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode("Dispozitii generale ale codului rutier");
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode("Vehiculele");
            DefaultMutableTreeNode t2_1 = new DefaultMutableTreeNode("Conditiile privind circulatia vehiculelor si controlul acestora");
            DefaultMutableTreeNode t2_2 = new DefaultMutableTreeNode("Inmatricularea, inregistrarea si radierea vehiculelor");
        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode("Conducatorii de vehicule"); 
            DefaultMutableTreeNode t3_1 = new DefaultMutableTreeNode("Dispozitii generale");
            DefaultMutableTreeNode t3_2 = new DefaultMutableTreeNode("Permisul de conducere");
        DefaultMutableTreeNode t4 = new DefaultMutableTreeNode("Semnalizare rutiera");
        DefaultMutableTreeNode t5 = new DefaultMutableTreeNode("Reguli generale ale codului rutier"); 
            DefaultMutableTreeNode t5_1 = new DefaultMutableTreeNode("Obligatiile participantilor la trafic"); 
            DefaultMutableTreeNode t5_2 = new DefaultMutableTreeNode("Reguli pentru circulatia vehiculelor"); 
            DefaultMutableTreeNode t5_3 = new DefaultMutableTreeNode("Reguli pentru alti participanti la trafic");
            DefaultMutableTreeNode t5_4 = new DefaultMutableTreeNode("Circulatia pe autostrazi");
            DefaultMutableTreeNode t5_5 = new DefaultMutableTreeNode("Obligatii in caz de accident");
            DefaultMutableTreeNode t5_6 = new DefaultMutableTreeNode("Circulatia autovehiculelor in traficul international");
        DefaultMutableTreeNode t6 = new DefaultMutableTreeNode("Infractiuni si Pedepse");
        DefaultMutableTreeNode t7 = new DefaultMutableTreeNode("Raspunderea contraventionala");
        DefaultMutableTreeNode t8 = new DefaultMutableTreeNode("Cai de atac impotriva procesului-verbal");
        DefaultMutableTreeNode t9 = new DefaultMutableTreeNode("Atributii ale unor ministere si ale altor autoritati ale administratiei publice");
        DefaultMutableTreeNode t10 = new DefaultMutableTreeNode("Dispozitii finale ale codului rutier");
    JButton bDeschide;
    
    public InterfataCursant_Legislatie(){
        super("Legislatie");
        Ascultator a = new Ascultator();
        bDeschide = new JButton("Deschide");
        bDeschide.addActionListener(a);
        
        tLegislatie.add(t1);
        tLegislatie.add(t2);
        tLegislatie.add(t3);
        tLegislatie.add(t4);
        tLegislatie.add(t5);
        tLegislatie.add(t6);
        tLegislatie.add(t7);
        tLegislatie.add(t8);
        tLegislatie.add(t9);
        tLegislatie.add(t10);
        
        t2.add(t2_1);
        t2.add(t2_2);
        
        t3.add(t3_1);
        t3.add(t3_2);
        
        t5.add(t5_1);
        t5.add(t5_2);
        t5.add(t5_3);
        t5.add(t5_4);
        t5.add(t5_5);
        t5.add(t5_6);   
        
        tree = new JTree(tLegislatie);
        
        getContentPane().add(tree, BorderLayout.NORTH);
        getContentPane().add(bDeschide, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    class Ascultator implements ActionListener{
        public void actionPerformed(ActionEvent e){ 
            if(e.getSource() == bDeschide){
                System.out.println(tree.getLastSelectedPathComponent());
                if(tree.getLastSelectedPathComponent().toString().equals(t1.toString())){ 
                    iCLA = new InterfataCursant_Legislatie_Articole(ct1);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t2_1.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct2_1);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t2_2.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct2_2);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t3_1.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct3_1);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t3_2.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct3_2);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t4.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct4);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t5_1.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct5_1);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t5_2.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct5_2);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t5_3.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct5_3);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t5_4.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct5_4);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t5_5.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct5_5);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t5_6.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct5_6);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t6.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct6);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t7.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct7);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t8.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct8);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t9.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct9);
                }
                if(tree.getLastSelectedPathComponent().toString().equals(t10.toString())){
                    iCLA = new InterfataCursant_Legislatie_Articole(ct10);
                }
                iCLA.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }     
        }
    }
}
