import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

class Logare extends JFrame{
    
    private InterfataCursant_Inregistrare iCI;
    private InterfataCursant iC;
    private InterfataAdministrator iA;
    private ListaCursanti_Conturi lista = new ListaCursanti_Conturi();
    
    private final String cursant = "Cursant";
    private final String administrator = "Administrator";
    private final String id_admin = "admin";
    private final String pass_admin = "admin";
    private JLabel lTipCont, lUtilizator, lParola;
    private JButton bSignIn, bSignUp;
    private JComboBox<String> CBCont;
    private JTextField TFUtilizator;
    private JPasswordField PWFParola;
    private JPanel p1, p2;
    
    public Logare(){
        super("Logare");
        AscultatorButoane a = new AscultatorButoane();
        Font f = new Font("bold", Font.BOLD, 15);
        
        bSignIn = new JButton("Sign In");
        bSignIn.addActionListener(a);
        bSignUp = new JButton("Sign Up");
        bSignUp.addActionListener(a);
        
        lTipCont = new JLabel("Tip Cont: ");
        lTipCont.setFont(f);  
        lUtilizator = new JLabel("Utilizator: ");
        lUtilizator.setFont(f);
        lParola = new JLabel("Parola: ");
        lParola.setFont(f);
        
        TFUtilizator = new JTextField(15);
        PWFParola = new JPasswordField(15);
        
        final String[] sList = {"Administrator", "Cursant"};
        CBCont = new JComboBox<>(sList);
        
        p1 = new JPanel();
        p1.setLayout(new GridLayout(3,2));
        p1.add(lTipCont);p1.add(CBCont);
        p1.add(lUtilizator); p1.add(TFUtilizator);
        p1.add(lParola); p1.add(PWFParola);
        
        p2 = new JPanel();
        p2.add(bSignIn);
        p2.add(bSignUp);
        
        getContentPane().add(p1, BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    }
    class AscultatorButoane implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if(ev.getSource()==bSignUp){
                setVisible(false);
                iCI = new InterfataCursant_Inregistrare();
            }
            if(ev.getSource()==bSignIn){
                String selectat = (String)CBCont.getSelectedItem();
                String parola = String.valueOf(PWFParola.getPassword());
                if(selectat.equals(cursant)){
                    if(lista.autentificareCursanti(TFUtilizator.getText(), parola)){
                        setVisible(false);
                        iC = new InterfataCursant(TFUtilizator.getText());
                        iC.setSize(300,150);
                    }else{
                        JOptionPane.showMessageDialog(null, "ID-ul sau parola nu sunt corecte!");
                    } 
                }
                if(selectat.equals(administrator)){ 
                    if(TFUtilizator.getText().equals(id_admin) && parola.equals(pass_admin)){
                        setVisible(false);
                        iA = new InterfataAdministrator();
                    }else{
                        JOptionPane.showMessageDialog(null, "ID-ul sau parola nu sunt corecte!");
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        Logare l = new Logare();
    }
}
