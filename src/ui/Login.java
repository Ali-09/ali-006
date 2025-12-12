package ui;

import bankService.dao.CardDaoImpl;
import bankService.model.Card;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel panelLoading;
    private JTextField nCard;
    private JPasswordField nip;
    private JButton button1;
    private JLabel labelCard;
    private JLabel labelNip;

    public Login() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nipValue = new String(nip.getPassword());
                String cardValue = nCard.getText();
                Card card = new Card();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelLoading);
                loginAction(frame, cardValue, nipValue);
            }
        });
    }

    private void loginAction(JFrame parent, String numberCard, String pin) {
        CardDaoImpl cardDao = new CardDaoImpl();
        LoadingDialog loading = new LoadingDialog(parent);

        SwingWorker<Card, Void> worker = new SwingWorker<>() {
            @Override
            protected Card doInBackground() throws Exception {
                Thread.sleep(3000); // simula carga
                return cardDao.findByNumberCardAndPin(numberCard, pin);
            }

            protected void done() {
                loading.dispose();
                try {
                    Card card = get();
                    if (card.isInitialized()) {
                        // JOptionPane.showMessageDialog(null,  "Registro encontrado: " + card.getNumberCard());
                        parent.dispose();
                        new Dashboard(card).showUI();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Tarjeta o NIP incorrectos");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        worker.execute();
        loading.setVisible(true); // mostrar spinner
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void showUI() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(this.panelLoading);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
