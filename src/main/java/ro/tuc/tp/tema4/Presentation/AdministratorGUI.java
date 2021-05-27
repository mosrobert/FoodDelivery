package ro.tuc.tp.tema4.Presentation;

import ro.tuc.tp.tema4.BusinessLayer.BaseProduct;
import ro.tuc.tp.tema4.BusinessLayer.CompositeProduct;
import ro.tuc.tp.tema4.BusinessLayer.DeliveryService;
import ro.tuc.tp.tema4.BusinessLayer.MenuItem;
import ro.tuc.tp.tema4.DataLayer.Serializator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.table.DefaultTableModel;

public class AdministratorGUI {

    private JFrame frmAdministrator;
    private JTable productsTable;
    private JTextField titluTxt;
    private JTextField ratingTxt;
    private JTextField caloriiTxt;
    private JTextField proteineTxt;
    private JTextField grasimiTxt;
    private JTextField sareTxt;
    private JTextField pretTxt;
    private JTextField compositeTitleTxt;
    private JTable table;
    private List<MenuItem> prod = new ArrayList<>();
    private DeliveryService ds = new DeliveryService();

    /**
     * Launch the application.
     */
    public static void Admin() {

        AdministratorGUI window = new AdministratorGUI();
        window.frmAdministrator.setVisible(true);
    }

    /**
     * Create the application.
     */
    public AdministratorGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmAdministrator = new JFrame();
        frmAdministrator.setTitle("Administrator");
        frmAdministrator.setBounds(100, 100, 1213, 517);
        frmAdministrator.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frmAdministrator.getContentPane().setLayout(null);

        JButton importBtn = new JButton("Import products");
        importBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(importBtn);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    ds.importProduct(file.getAbsolutePath());
                }

            }
        });
        importBtn.setBounds(527, 18, 129, 21);
        frmAdministrator.getContentPane().add(importBtn);
        /**
         * Butonul care adauga un nou produs la lista de produse
         */
        JButton addBtn = new JButton("Add product");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseProduct p = new BaseProduct(titluTxt.getText(),
                        Double.parseDouble(ratingTxt.getText()),
                        Integer.parseInt(caloriiTxt.getText()),
                        Integer.parseInt(proteineTxt.getText()),
                        Integer.parseInt(grasimiTxt.getText()),
                        Integer.parseInt(sareTxt.getText()),
                        Integer.parseInt(pretTxt.getText()));
                ds.addProduct(p);
                Serializator.serializareProduse();
                updateTable(productsTable,DeliveryService.getProducts());
                JOptionPane.showMessageDialog(addBtn,"Products added!");
            }
        });
        addBtn.setBounds(466, 418, 129, 21);
        frmAdministrator.getContentPane().add(addBtn);
        /**
         * Butonul care salveaza modificarile facute unui produs din lista de produse
         */
        JButton saveBtn = new JButton("Save edit");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = productsTable.getRowCount();
                for (int i = 0; i < n; i++) {
                    BaseProduct p = getProduct(i);
                    ds.editProduct(p.getTitle(), p);
                }
                updateTable(productsTable, DeliveryService.getProducts());
            }
        });
        saveBtn.setBounds(527, 107, 129, 21);
        frmAdministrator.getContentPane().add(saveBtn);
        /**
         * Butonul care renunta la modificarea unui produs
         */
        JButton cancelBtn = new JButton("Cancel edit");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable(productsTable, DeliveryService.getProducts());
            }
        });
        cancelBtn.setBounds(527, 154, 129, 21);
        frmAdministrator.getContentPane().add(cancelBtn);
        /**
         * Butonul care adauga un produs compus
         */
        JButton compositeBtn = new JButton("Composite product");
        compositeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<BaseProduct> prod1 = new ArrayList<>();
                prod.forEach(p -> {
                    if (p instanceof BaseProduct) prod1.add((BaseProduct) p);
                });
                ds.createProduct(compositeTitleTxt.getText(), prod1);
                prod=new ArrayList<>();
                compositeTitleTxt.setText("");
                updateTable(table,prod);
                updateTable(productsTable,DeliveryService.getProducts());
            }
        });
        compositeBtn.setBounds(948, 418, 144, 21);
        frmAdministrator.getContentPane().add(compositeBtn);
        /**
         * Butonul care deschide fereastra pentru generarea raportului
         */
        JButton generateBtn = new JButton("Generate reports");
        generateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                GenerateReports rep = new GenerateReports();
                rep.GenerateRep();
            }
        });
        generateBtn.setBounds(527, 243, 144, 21);
        frmAdministrator.getContentPane().add(generateBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 436, 330);
        frmAdministrator.getContentPane().add(scrollPane);

        productsTable = new JTable();
        productsTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"
                }
        ));
        scrollPane.setViewportView(productsTable);

        JLabel lblNewLabel = new JLabel("Titlu");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(26, 363, 45, 16);
        frmAdministrator.getContentPane().add(lblNewLabel);

        titluTxt = new JTextField();
        titluTxt.setBounds(81, 364, 96, 19);
        frmAdministrator.getContentPane().add(titluTxt);
        titluTxt.setColumns(10);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(729, 18, 436, 330);
        frmAdministrator.getContentPane().add(scrollPane_1);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"
                }
        ));
        scrollPane_1.setViewportView(table);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRating.setBounds(26, 397, 45, 21);
        frmAdministrator.getContentPane().add(lblRating);

        JLabel lblCalorii = new JLabel("Calorii");
        lblCalorii.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCalorii.setBounds(26, 435, 45, 16);
        frmAdministrator.getContentPane().add(lblCalorii);

        ratingTxt = new JTextField();
        ratingTxt.setColumns(10);
        ratingTxt.setBounds(81, 398, 96, 19);
        frmAdministrator.getContentPane().add(ratingTxt);

        caloriiTxt = new JTextField();
        caloriiTxt.setColumns(10);
        caloriiTxt.setBounds(81, 436, 96, 19);
        frmAdministrator.getContentPane().add(caloriiTxt);

        JLabel lblProtein = new JLabel("Proteine");
        lblProtein.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProtein.setBounds(239, 363, 56, 16);
        frmAdministrator.getContentPane().add(lblProtein);

        JLabel lblGrasimi = new JLabel("Gr\u0103simi");
        lblGrasimi.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGrasimi.setBounds(239, 399, 56, 16);
        frmAdministrator.getContentPane().add(lblGrasimi);

        JLabel lblSodiu = new JLabel("Sodiu");
        lblSodiu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSodiu.setBounds(239, 435, 45, 16);
        frmAdministrator.getContentPane().add(lblSodiu);

        proteineTxt = new JTextField();
        proteineTxt.setColumns(10);
        proteineTxt.setBounds(294, 364, 96, 19);
        frmAdministrator.getContentPane().add(proteineTxt);

        grasimiTxt = new JTextField();
        grasimiTxt.setColumns(10);
        grasimiTxt.setBounds(294, 398, 96, 19);
        frmAdministrator.getContentPane().add(grasimiTxt);

        sareTxt = new JTextField();
        sareTxt.setColumns(10);
        sareTxt.setBounds(294, 436, 96, 19);
        frmAdministrator.getContentPane().add(sareTxt);

        JLabel lblPrice = new JLabel("Pre\u021B");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrice.setBounds(446, 367, 45, 16);
        frmAdministrator.getContentPane().add(lblPrice);

        pretTxt = new JTextField();
        pretTxt.setColumns(10);
        pretTxt.setBounds(499, 364, 96, 19);
        frmAdministrator.getContentPane().add(pretTxt);
        /**
         * Butonul care muta un produs pentru a fi adaugat la produse compuse
         */
        JButton moveBtn = new JButton(">>>>>");
        moveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = productsTable.getSelectedRows();
                for (int i : rows) {
                    BaseProduct p = getProduct(i);
                    prod.add(p);
                }
                updateTable(table, prod);
            }
        });
        moveBtn.setBounds(527, 62, 129, 21);
        frmAdministrator.getContentPane().add(moveBtn);

        compositeTitleTxt = new JTextField();
        compositeTitleTxt.setBounds(973, 363, 161, 21);
        frmAdministrator.getContentPane().add(compositeTitleTxt);
        compositeTitleTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Titlu");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(888, 365, 75, 17);
        frmAdministrator.getContentPane().add(lblNewLabel_1);
        /**
         * Butonul care sterge un produs selectat
         */
        JButton deleteBtn = new JButton("Delete product");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MenuItem> deletedItems=new ArrayList<>();
                int[] rows = productsTable.getSelectedRows();
                for (int i : rows) {
                    BaseProduct p = getProduct(i);
                    deletedItems.add(p);
                }
                deletedItems.forEach(p->ds.deleteProduct(p));
                updateTable(productsTable,DeliveryService.getProducts());
            }
        });
        deleteBtn.setBounds(527, 195, 129, 21);
        frmAdministrator.getContentPane().add(deleteBtn);
        updateTable(productsTable,DeliveryService.getProducts());
    }

    /**
     * face update la elementele din tabel
     * @param table tabelul la care se face update
     * @param items elementele la care se face update
     */
    private void updateTable(JTable table, List<MenuItem> items) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"}
        );
        Object[] row = new Object[7];
        for (MenuItem p : items) {
            if (p instanceof BaseProduct) {
                row[0] = ((BaseProduct) p).getTitle();
                row[1] = ((BaseProduct) p).getRating();
                row[2] = ((BaseProduct) p).getCalories();
                row[3] = ((BaseProduct) p).getProtein();
                row[4] = ((BaseProduct) p).getFat();
                row[5] = ((BaseProduct) p).getSodium();
                row[6] = ((BaseProduct) p).getPrice();
            } else {
                row[0] = ((CompositeProduct) p).getTitle();
                row[1]="";
                row[2]="";
                row[3]="";
                row[4]="";
                row[5]="";
                row[6] = p.computePrice();
            }
            model.addRow(row);
        }
        table.setModel(model);
    }

    /**
     *ia produsul de pe o anumita linie
     * @param i linia de pe care se ia produsul
     * @return returneaza valorile produsului
     */
    private BaseProduct getProduct(int i) {
        return new BaseProduct(String.valueOf(productsTable.getValueAt(i, 0)),
                Double.parseDouble(String.valueOf(productsTable.getValueAt(i, 1))),
                Integer.parseInt(String.valueOf(productsTable.getValueAt(i, 2))),
                Integer.parseInt(String.valueOf(productsTable.getValueAt(i, 3))),
                Integer.parseInt(String.valueOf(productsTable.getValueAt(i, 4))),
                Integer.parseInt(String.valueOf(productsTable.getValueAt(i, 5))),
                Integer.parseInt(String.valueOf(productsTable.getValueAt(i, 6))));
    }
}
