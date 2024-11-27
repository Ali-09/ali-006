import bankService.DatabaseConn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private JPanel panelMain;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;

    public Login() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT * FROM card";
                try (Connection conn = DatabaseConn.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query);
                     ResultSet rs = stmt.executeQuery()) {
                    JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
                    System.out.println(rs);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bank");
        frame.setContentPane(new Login().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
