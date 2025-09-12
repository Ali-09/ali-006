package ui;

import javax.swing.*;

public class Dashboard {
    private JPanel panelDashboard;
    private JTextField textField1;
    private JButton depositarButton;
    private JButton btnWithdraw;
    private JButton btnExit;

    public Dashboard() {
        System.out.println("Dashboard View");
    }

    public void showUI() {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(panelDashboard);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}