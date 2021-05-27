package ro.tuc.tp.tema4.Presentation;
import ro.tuc.tp.tema4.DataLayer.Serializator;
import ro.tuc.tp.tema4.model.Client;
import ro.tuc.tp.tema4.model.Inregistrare;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {

    private JFrame frmRegister;
    private JTextField nameTxt;
    private JTextField emailTxt;
    private JTextField usernameTxt;
    private JPasswordField passwordTxt;

    /**
     * Launch the application.
     */
    public static void Regist() {
        Register window = new Register();
        window.frmRegister.setVisible(true);
    }

    /**
     * Create the application.
     */
    public Register() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmRegister = new JFrame();
        frmRegister.setTitle("Register");
        frmRegister.setBounds(100, 100, 613, 419);
        frmRegister.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frmRegister.getContentPane().setLayout(null);

        nameTxt = new JTextField();
        nameTxt.setBounds(117, 69, 173, 27);
        frmRegister.getContentPane().add(nameTxt);
        nameTxt.setColumns(10);

        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setBounds(49, 71, 44, 20);
        frmRegister.getContentPane().add(lblNewLabel);

        emailTxt = new JTextField();
        emailTxt.setColumns(10);
        emailTxt.setBounds(117, 126, 173, 27);
        frmRegister.getContentPane().add(emailTxt);

        usernameTxt = new JTextField();
        usernameTxt.setColumns(10);
        usernameTxt.setBounds(117, 188, 173, 27);
        frmRegister.getContentPane().add(usernameTxt);

        JLabel lblEmail = new JLabel("email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEmail.setBounds(49, 128, 44, 20);
        frmRegister.getContentPane().add(lblEmail);

        JLabel lblUsername = new JLabel("username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUsername.setBounds(49, 190, 58, 20);
        frmRegister.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPassword.setBounds(49, 253, 58, 20);
        frmRegister.getContentPane().add(lblPassword);

        JButton createBtn = new JButton("Create account");
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client cl=new Client(nameTxt.getText(), emailTxt.getText(),usernameTxt.getText(),passwordTxt.getText() );
                cl.setId(Inregistrare.getCl().size()+1);
                Inregistrare.addUser(cl);
                Serializator.serializareClienti();
                JOptionPane.showMessageDialog(createBtn,"Cont creat!");
            }
        });
        createBtn.setBounds(117, 315, 121, 27);
        frmRegister.getContentPane().add(createBtn);

        JLabel lblCreateAccount = new JLabel("Create account");
        lblCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCreateAccount.setBounds(242, 10, 139, 32);
        frmRegister.getContentPane().add(lblCreateAccount);

        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(117, 247, 173, 27);
        frmRegister.getContentPane().add(passwordTxt);
    }
}
