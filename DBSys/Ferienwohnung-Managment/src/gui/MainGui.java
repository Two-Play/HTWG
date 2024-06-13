package gui;

import functions.FetchData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame {
    private JPanel MainPanel;
    private JLabel landLabel;
    private JLabel sucheLabel;
    private JComboBox landDropdown;
    private JTable mainTable;
    private JLabel anreiseLabel;
    private JTextField textFieldAnreise;
    private JButton button1;
    private JLabel abreiseLabel;
    private JButton button2;
    private JTextField textFieldAbreise;
    private JButton suchenBtn;
    private JScrollPane scroolPaneList;
    private JButton buchenButton;
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

        textFieldAnreise.setText("10-07-2024");
        textFieldAbreise.setText("17-07-2024");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Durchschnittliche Bewertung");
       // model.addColumn("Land");
        model.addColumn("Preis");

        mainTable.setModel(model);

        suchenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String land = landDropdown.getSelectedItem().toString();
                String anreise = textFieldAnreise.getText();
                String abreise = textFieldAbreise.getText();

                if (anreise.isEmpty() || abreise.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie ein Anreise- und Abreisedatum ein", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // get chackboxes from panel
                Component[] components = ausstattungsPane.getComponents();

                fetchData.ferienwohnungsuche(land, anreise, abreise, model, components);
            }
        });
    }
}
