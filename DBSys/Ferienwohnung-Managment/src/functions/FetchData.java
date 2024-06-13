package functions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
            //e.printStackTrace();
            System.out.println("Connection to database failed");
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
            //e.printStackTrace();
            System.out.println("Error while fetching data");
        }
    }

    public void getFurnishings(JPanel ausstattungsPane) {
        final String sqlStatement = "SELECT name FROM ausstattung ORDER BY name ASC";

        try {
            //TODO: Fix bug if reconecting, stmt is null
            ResultSet rs = stmt.executeQuery(sqlStatement);

            while (rs.next()) {
                String furnishing = rs.getString("name");
                ausstattungsPane.add(new JCheckBox(furnishing));
            }

        } catch (SQLException e) {
            // show dialog with error message
            JOptionPane.showMessageDialog(null, "Error while fetching data", "Error", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
            System.out.println("Error while fetching data");
        }
    }

    public void ferienwohnungsuche(String land, String anreise, String abreise, DefaultTableModel model, Component[] components) {

        StringBuilder sqlStatement = new StringBuilder("SELECT\n" +
                "    FERIENWOHNUNG.ferienwohnung_id,\n" +
                "    FERIENWOHNUNG.name,\n" +
                "FERIENWOHNUNG.preis,\n" +
               // "Land.name,\n" +
                "    AVG(BUCHUNG.sternebewertung) AS durchschnittliche_bewertung\n" +
                "FROM FERIENWOHNUNG, BESITZT_AUSSTATTUNG, AUSSTATTUNG, BUCHUNG, LAND\n" +
                "WHERE LAND.name = ?\n");
                for (Component component : components) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) component;
                        if (checkBox.isSelected()) {
                            sqlStatement.append("AND AUSSTATTUNG.name = '").append(checkBox.getText()).append("'\n");
                        }
                    }
                }
                sqlStatement.append("  AND BUCHUNG.buchungsnummer NOT IN (\n" + "    SELECT buchungsnummer\n" + "    FROM BUCHUNG\n" +
                        "    WHERE TO_DATE(buchungsstartdatum, 'DD-MM-YYYY') < TO_DATE(?, 'DD-MM-YYYY')\n" +
                        "    AND TO_DATE(buchungsenddatum, 'DD-MM-YYYY') > TO_DATE(?, 'DD-MM-YYYY')\n" + "    )\n" +
                        "AND FERIENWOHNUNG.ferienwohnung_id = BESITZT_AUSSTATTUNG.ferienwohnung\n" +
                        "AND BESITZT_AUSSTATTUNG.ausstattung = AUSSTATTUNG.name\n" +
                        "AND FERIENWOHNUNG.ferienwohnung_id = BUCHUNG.ferienwohnung\n" +
                        "AND FERIENWOHNUNG.land = LAND.land_id\n" + "GROUP BY\n" +
                        "    FERIENWOHNUNG.ferienwohnung_id, FERIENWOHNUNG.name, FERIENWOHNUNG.preis"                   );

        System.out.println("SQL: " + sqlStatement.toString());

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlStatement.toString());
            pstmt.setString(1, land);
            pstmt.setString(2, anreise);
            pstmt.setString(3, abreise);

            ResultSet rs = pstmt.executeQuery();

            conn.commit();

            model.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("ferienwohnung_id");
                String name = rs.getString("name");
                String bewertung = rs.getString("durchschnittliche_bewertung");
                //String landName = rs.getString("land");
                String preis = rs.getString("preis");


                model.addRow(new Object[]{id, name, bewertung, preis});
            }

        } catch (SQLException e) {
            // show dialog with error message
            JOptionPane.showMessageDialog(null, "Error while fetching data", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error while fetching data:");
            System.out.println(e.getMessage());
        }
    }

}
