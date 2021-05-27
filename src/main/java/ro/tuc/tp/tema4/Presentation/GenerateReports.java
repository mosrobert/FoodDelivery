package ro.tuc.tp.tema4.Presentation;
import ro.tuc.tp.tema4.BusinessLayer.DeliveryService;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateReports {

    private JFrame frmGenerateReports;
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt6;
    private JTextField txt5;
    private JTextField txt4;


    public GenerateReports() {
        initialize();
    }
    public static void GenerateRep() {
        GenerateReports window = new GenerateReports();
        window.frmGenerateReports.setVisible(true);
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmGenerateReports = new JFrame();
        frmGenerateReports.setTitle("Generate Reports");
        frmGenerateReports.setBounds(100, 100, 720, 490);
        frmGenerateReports.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frmGenerateReports.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Interval de timp");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setBounds(36, 29, 113, 23);
        frmGenerateReports.getContentPane().add(lblNewLabel);

        txt1 = new JTextField();
        txt1.setBounds(108, 77, 96, 19);
        frmGenerateReports.getContentPane().add(txt1);
        txt1.setColumns(10);

        txt2 = new JTextField();
        txt2.setBounds(108, 121, 96, 19);
        frmGenerateReports.getContentPane().add(txt2);
        txt2.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("De la:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(36, 77, 62, 16);
        frmGenerateReports.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("P\u00E2n\u0103 la:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1_1.setBounds(36, 124, 62, 16);
        frmGenerateReports.getContentPane().add(lblNewLabel_1_1);

        JLabel lblProduseComandateDe = new JLabel("Produse comandate de un num\u0103r de ori");
        lblProduseComandateDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblProduseComandateDe.setBounds(36, 175, 223, 23);
        frmGenerateReports.getContentPane().add(lblProduseComandateDe);

        txt3 = new JTextField();
        txt3.setColumns(10);
        txt3.setBounds(108, 208, 96, 19);
        frmGenerateReports.getContentPane().add(txt3);

        JLabel lblNewLabel_1_1_1 = new JLabel("Num\u0103r de ");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1_1_1.setBounds(36, 208, 62, 16);
        frmGenerateReports.getContentPane().add(lblNewLabel_1_1_1);

        JLabel lblProduseComandatentro = new JLabel("Produse comandate \u00EEntr-o data specificat\u0103:");
        lblProduseComandatentro.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblProduseComandatentro.setBounds(36, 391, 241, 23);
        frmGenerateReports.getContentPane().add(lblProduseComandatentro);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Data:");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1_1_1_1.setBounds(36, 424, 62, 16);
        frmGenerateReports.getContentPane().add(lblNewLabel_1_1_1_1);

        txt6 = new JTextField();
        txt6.setColumns(10);
        txt6.setBounds(108, 424, 96, 19);
        frmGenerateReports.getContentPane().add(txt6);

        txt5 = new JTextField();
        txt5.setColumns(10);
        txt5.setBounds(108, 351, 96, 19);
        frmGenerateReports.getContentPane().add(txt5);

        txt4 = new JTextField();
        txt4.setColumns(10);
        txt4.setBounds(108, 307, 96, 19);
        frmGenerateReports.getContentPane().add(txt4);

        JLabel lblClientiCareAu = new JLabel("Clienti care au comandat de un anumit numar de ori");
        lblClientiCareAu.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblClientiCareAu.setBounds(36, 259, 293, 23);
        frmGenerateReports.getContentPane().add(lblClientiCareAu);

        JLabel lblNewLabel_1_1_1_2 = new JLabel("Num\u0103r de ");
        lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1_1_1_2.setBounds(36, 307, 62, 16);
        frmGenerateReports.getContentPane().add(lblNewLabel_1_1_1_2);

        JLabel lblNewLabel_1_1_1_3 = new JLabel("Valoarea minima");
        lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1_1_1_3.setBounds(10, 354, 88, 16);
        frmGenerateReports.getContentPane().add(lblNewLabel_1_1_1_3);

        JButton gen1Btn = new JButton("Generate");
        gen1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService ds=new DeliveryService();
                String mesaj=ds.generateReports1(Integer.parseInt(txt1.getText()),Integer.parseInt(txt2.getText()));
                JOptionPane.showMessageDialog(gen1Btn,mesaj);
            }
        });
        gen1Btn.setBounds(398, 90, 96, 21);
        frmGenerateReports.getContentPane().add(gen1Btn);

        JButton gen2Btn = new JButton("Generate");
        gen2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService ds=new DeliveryService();
                String mesaj=ds.generateReports2(Integer.parseInt(txt3.getText()));
                JOptionPane.showMessageDialog(gen2Btn,mesaj);
            }
        });
        gen2Btn.setBounds(398, 194, 96, 21);
        frmGenerateReports.getContentPane().add(gen2Btn);

        JButton gen3Btn = new JButton("Generate");
        gen3Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService ds=new DeliveryService();
                String mesaj=ds.generateReports3(Integer.parseInt(txt4.getText()),Integer.parseInt(txt5.getText()));
                JOptionPane.showMessageDialog(gen3Btn,mesaj);
            }
        });
        gen3Btn.setBounds(398, 319, 96, 21);
        frmGenerateReports.getContentPane().add(gen3Btn);

        JButton gen4Btn = new JButton("Generate");
        gen4Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryService ds=new DeliveryService();
                String mesaj=ds.generateReports4(txt6.getText());
                JOptionPane.showMessageDialog(gen4Btn,mesaj);
            }
        });
        gen4Btn.setBounds(398, 407, 96, 21);
        frmGenerateReports.getContentPane().add(gen4Btn);
    }

}
