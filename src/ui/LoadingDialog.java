package ui;

import javax.swing.*;
import java.awt.*;

public class LoadingDialog extends JDialog {
    private JPanel contentPane;
    private JProgressBar progressBar;
    private JLabel label;

    public LoadingDialog(JFrame parent) {
        super(parent, "Cargando...", true);

        progressBar.setUI(new javax.swing.plaf.metal.MetalProgressBarUI());
        progressBar.setIndeterminate(true);


        setSize(250, 100);
        setContentPane(contentPane);
        setUndecorated(true);

        setLocationRelativeTo(parent);
    }
}
