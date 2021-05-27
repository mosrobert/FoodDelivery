package ro.tuc.tp.tema4.Presentation;

import ro.tuc.tp.tema4.BusinessLayer.*;
import ro.tuc.tp.tema4.DataLayer.Serializator;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.table.DefaultTableModel;

public class ClientGUI {

    private JFrame frame;
    private JTextField searchTxt;
    private JTable productsTable;
    private JTable orderTable;
    private int id;
    private List<MenuItem> prod = new ArrayList<>();
    private DeliveryService ds = new DeliveryService();

    /**
     * Launch the application.
     */
    public void Client(int id) {
        this.id = id;
        ClientGUI window = new ClientGUI();
        window.frame.setVisible(true);
    }

    /**
     * Create the application.
     */
    public ClientGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 426);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        searchTxt = new JTextField();
        searchTxt.setBounds(549, 20, 117, 19);
        frame.getContentPane().add(searchTxt);
        searchTxt.setColumns(10);

        JLabel lblNewLabel = new JLabel("Search:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(477, 23, 62, 16);
        frame.getContentPane().add(lblNewLabel);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Title", "Rating", "Fat", "Sodium", "Protein", "Price", "Calories"}));
        comboBox.setBounds(549, 49, 117, 19);
        frame.getContentPane().add(comboBox);

        JLabel lblSortBy = new JLabel("Sort by:");
        lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblSortBy.setBounds(477, 52, 62, 16);
        frame.getContentPane().add(lblSortBy);
        /**
         * Butonul pentru plasarea unei comenzi
         */
        JButton orderBtn = new JButton("Place order");
        orderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order(DeliveryService.getOrders().keySet().size() + 1, id, LocalDateTime.now());
                ds.createOrder(order, prod);
                Serializator.serializareComenzi();
                prod = new ArrayList<>();
                updateTable(orderTable, prod);
                JOptionPane.showMessageDialog(orderBtn, "Comanda inregistrata!");
            }
        });
        orderBtn.setBounds(549, 317, 117, 21);
        frame.getContentPane().add(orderBtn);
        /**
         * Butonul pentru cautarea unui produs
         */
        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable(productsTable, ds.searchProd(String.valueOf(comboBox.getSelectedItem()), searchTxt.getText()));
            }
        });
        searchBtn.setBounds(549, 88, 117, 21);
        frame.getContentPane().add(searchBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 20, 457, 346);
        frame.getContentPane().add(scrollPane);

        productsTable = new JTable();
        productsTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"
                }
        ));
        scrollPane.setViewportView(productsTable);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(721, 20, 455, 346);
        frame.getContentPane().add(scrollPane_1);

        orderTable = new JTable();
        orderTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"
                }
        ));
        scrollPane_1.setViewportView(orderTable);
        /**
         * Muta un produs pentru a fi cumparat
         */
        JButton moveBtn = new JButton(">>>>>>");
        moveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = productsTable.getSelectedRows();
                for (int i : rows) {
                    String title = String.valueOf(productsTable.getValueAt(i, 0));
                    MenuItem m = DeliveryService.getProducts().stream()
                            .filter(p ->
                                    (p instanceof BaseProduct) ? ((BaseProduct) p).getTitle().equals(title) : ((CompositeProduct) p).getTitle().equals(title)
                            ).collect(Collectors.toList()).get(0);
                    prod.add(m);
                }
                updateTable(orderTable,prod);
            }
        });
        moveBtn.setBounds(549, 168, 117, 21);
        frame.getContentPane().add(moveBtn);

        JLabel lblNewLabel_1 = new JLabel("Pret total:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(494, 353, 68, 26);
        frame.getContentPane().add(lblNewLabel_1);
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
        row[1] = row[2] = row[3] = row[4] = row[5] = "";
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
}
