package functions;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class FetchData {

    final int CONNECTION_TIMEOUT = 5;

    Connection conn;
    Statement stmt;
    
    public FetchData(final String url, final String user, final String password) {
        final JDialog dialog = getInitDialog("Connecting to database...", "Info");

        System.out.println("Connecting to database...");
        // hier wird die Verbindung zur Datenbank aufgebaut
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            DriverManager.setLoginTimeout(CONNECTION_TIMEOUT);
            conn = DriverManager.getConnection(url, user, password);
    
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            //conn.setNetworkTimeout(null, 5000);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            dialog.dispose();
            System.out.println("Connected to database");
            //JOptionPane.showMessageDialog(null, "Connected to database", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            dialog.dispose();

            // Create a custom dialog with "Reconnect" and "Exit" buttons
            Object[] options = {"Reconnect", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Verbindung zur Datenbank fehlgeschlagen", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

            if (choice == JOptionPane.YES_OPTION) {
                // Retry the connection
                new FetchData(url, user, password);
            } else {
                // Exit the application
                System.exit(0);
            }
            e.printStackTrace();
        }
    }

    private static JDialog getInitDialog(final String message, final String title) {
        final JOptionPane optionPane = new JOptionPane( message,
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
                null, new Object[] {}, null);

        final JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setModal(false);
        dialog.setVisible(true);

        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        return dialog;
    }


    public void getCountries(JComboBox landDropdown) {
        final String sqlStatement = "SELECT name FROM land ORDER BY name ASC";
        List<String> countriesList = null;

        try {
            ResultSet rs = stmt.executeQuery(sqlStatement);

            while (rs.next()) {
                String country = rs.getString("name");
                landDropdown.addItem(country);
            }

        } catch (SQLException e) {
            // show dialog with error message
            JOptionPane.showMessageDialog(null, "Error while fetching data", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void getFurnishings(JPanel ausstattungsPane) {
        final String sqlStatement = "SELECT name FROM ausstattung ORDER BY name ASC";

        try {
            ResultSet rs = stmt.executeQuery(sqlStatement);

            while (rs.next()) {
                String furnishing = rs.getString("name");
                ausstattungsPane.add(new JCheckBox(furnishing));
            }

        } catch (SQLException e) {
            // show dialog with error message
            JOptionPane.showMessageDialog(null, "Error while fetching data", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
