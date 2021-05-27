package ro.tuc.tp.tema4.Presentation;

import ro.tuc.tp.tema4.model.Inregistrare;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.stream.Collectors;
import javax.swing.JPasswordField;

public class Login {

    private JFrame frmLogin;
    private JTextField username;
    private JPasswordField password;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Login window = new Login();
        window.frmLogin.setVisible(true);
    }

    /**
     * Create the application.
     */
    public Login() {
        initialize();
        frmLogin.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmLogin = new JFrame();
        frmLogin.setTitle("Login");
        frmLogin.setBounds(100, 100, 450, 300);
        frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLogin.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("DeliveryService");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(163, 10, 129, 34);
        frmLogin.getContentPane().add(lblNewLabel);

        username = new JTextField();
        username.setBounds(122, 103, 154, 25);
        frmLogin.getContentPane().add(username);
        username.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("username");
        lblNewLabel_1.setBounds(22, 106, 68, 19);
        frmLogin.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("password");
        lblNewLabel_1_1.setBounds(22, 152, 68, 19);
        frmLogin.getContentPane().add(lblNewLabel_1_1);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String uname = username.getText();
                String pass = password.getText();
                if (Inregistrare.isClient(uname, pass)) {
                    JOptionPane.showMessageDialog(frmLogin, "Authentication succes");
                    ClientGUI cli = new ClientGUI();
                    cli.Client(Inregistrare.getCl().stream().filter(c->c.getUsername().equals(uname)&&c.getParola().equals(pass)).collect(Collectors.toList()).get(0).getId());
                } else if (uname.equals("admin") && pass.equals("password")) {
                    JOptionPane.showMessageDialog(frmLogin, "Authentication succes");
                    AdministratorGUI adm = new AdministratorGUI();
                    adm.Admin();
                } else if (uname.equals("employee") && pass.equals("password")) {
                    JOptionPane.showMessageDialog(frmLogin, "Authentication succes");
                    EmployeeGUI emp = new EmployeeGUI();
                    emp.Emp();
                } else {
                    JOptionPane.showMessageDialog(frmLogin, "Authentication fail");
                }
            }
        });
        btnNewButton.setBounds(82, 205, 85, 21);
        frmLogin.getContentPane().add(btnNewButton);

        JButton btnSignUp = new JButton("Sign up");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Register reg = new Register();
                reg.Regist();
            }
        });
        btnSignUp.setBounds(191, 205, 85, 21);
        frmLogin.getContentPane().add(btnSignUp);

        password = new JPasswordField();
        password.setBounds(122, 146, 154, 25);
        frmLogin.getContentPane().add(password);
    }
}


