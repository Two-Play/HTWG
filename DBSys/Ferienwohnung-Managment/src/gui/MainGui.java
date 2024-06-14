package gui;

import functions.FetchData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JButton stornoButton;
    private JScrollPane ausstattungScrollPane;
    private JTable table1;
    private JLabel ausstattungTable;
    private JPanel ausstattungsPane;

    protected class Functions {
        public static String getKundenIdInput() {
            return JOptionPane.showInputDialog(null, "Bitte geben Sie Ihre Kunden-ID ein", "Kunden-ID", JOptionPane.QUESTION_MESSAGE);
        }
    }

    public MainGui() {

        final String[] gAnreise = new String[1];
        final String[] gAbreise = new String[1];

        String url = "jdbc:oracle:thin:@oracle19c.in.htwg-konstanz.de:1521:ora19c";
        String user = "dbsys41";
        String password = "dbsys41";

//        String user = "dbsys49";
//        String password = "dbsys49";

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

        buchenButton.setEnabled(false);

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are uneditable
                return false;
            }
        };
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

                gAnreise[0] = anreise;
                gAbreise[0] = abreise;

                // get chackboxes from panel
                Component[] components = ausstattungsPane.getComponents();
                fetchData.ferienwohnungsuche(land, anreise, abreise, model, components);
            }
        });

        mainTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                System.out.println("focusGained");
                buchenButton.setEnabled(true);
            }

//            public void focusLost(java.awt.event.FocusEvent evt) {
//                System.out.println("focusLost");
//                buchenButton.setEnabled(false);
//            }
        });

        mainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String ferienwohnung_id = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString();
                    fetchData.buchung(Functions.getKundenIdInput(), ferienwohnung_id, gAnreise[0], gAbreise[0]);
                    suchenBtn.doClick();
                }
            }
        });


        buchenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // get row of selected table
                String ferienwohnung_id = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString();
                fetchData.buchung(Functions.getKundenIdInput(), ferienwohnung_id, gAnreise[0], gAbreise[0]);
                suchenBtn.doClick();
            }
        });
    }
}
