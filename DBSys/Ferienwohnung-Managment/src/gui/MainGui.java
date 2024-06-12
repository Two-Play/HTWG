package gui;

import functions.FetchData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainGui extends JFrame {
    private JPanel MainPanel;
    private JLabel landLabel;
    private JLabel sucheLabel;
    private JComboBox landDropdown;
    private JTable mainTable;
    private JLabel anreiseLabel;
    private JTextField textField1;
    private JButton button1;
    private JLabel abreiseLabel;
    private JButton button2;
    private JTextField textField2;
    private JButton suchenBtn;
    private JScrollPane scroolPaneList;
    private JButton button3;
    private JButton button4;
    private JScrollPane ausstattungScrollPane;
    private JTable table1;
    private JLabel ausstattungTable;
    private JPanel ausstattungsPane;

    public MainGui() {

        String url = "jdbc:oracle:thin:@oracle19c.in.htwg-konstanz.de:1521:ora19c";
        String user = "dbsys41";
        String password = "dbsys41";

        FetchData fetchData;
        fetchData = new FetchData(url, user, password);


        /// GUI ///

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(1000, 800);
        setTitle("Ferienwohnung Buchungssystem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.add(MainPanel);

        ausstattungsPane.setLayout(new BoxLayout(ausstattungsPane, BoxLayout.Y_AXIS));

        fetchData.getFurnishings(ausstattungsPane);

        fetchData.getCountries(landDropdown);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Vorname");
        model.addColumn("Anreise");
        model.addColumn("Abreise");
        model.addColumn("Personen");


        mainTable.setModel(model);

        model.addRow(new Object[]{1, "Mustermann", "Max", "01.01.2020", "10.01.2020", 2});
           model.addRow(new Object[]{2, "Musterfrau", "Erika", "15.01.2020", "20.01.2020", 1});
    }
}
