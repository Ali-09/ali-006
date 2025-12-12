package ui;

import bankService.dao.CardDaoImpl;
import bankService.dao.TransactionDao;
import bankService.dao.TransactionDaoImpl;
import bankService.model.AtmLocation;
import bankService.model.Card;
import bankService.model.TransactionType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction {
    private JPanel panelCash;
    private JButton btnAction;
    private JButton btnCancel;
    private JTextField textAmount;
    private JLabel labelAmount;
    private Card card;
    private String transactionTypeString;

    public Transaction(Card card, String transactionType) {
        this.card = card;
        this.transactionTypeString = transactionType;
        btnAction.setText(transactionType);

        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransaction();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToDashboard();
            }
        });
    }

    private void handleTransaction() {
        String amountText = textAmount.getText();
        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(panelCash, "Por favor, ingrese un monto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(panelCash, "El monto debe ser positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCash);
            LoadingDialog loading = new LoadingDialog(frame);

            SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    CardDaoImpl cardDao = new CardDaoImpl();
                    double currentBalance = card.getLinkedBalance();
                    double newBalance;
                    TransactionType type;

                    if ("Deposit".equals(transactionTypeString)) {
                        newBalance = currentBalance + amount;
                        type = TransactionType.DEPOSIT;
                    } else { // Withdraw
                        if (currentBalance < amount) {
                            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(panelCash, "Fondos insuficientes.", "Error", JOptionPane.ERROR_MESSAGE));
                            return false;
                        }
                        newBalance = currentBalance - amount;
                        type = TransactionType.WITHDRAWAL;
                    }

                    cardDao.updateBalance(card.getId(), newBalance);
                    card.setLinkedBalance(newBalance);

                    TransactionDao transactionDao = new TransactionDaoImpl();
                    bankService.model.Transaction transaction = new bankService.model.Transaction(
                            card.getId(),
                            type,
                            amount,
                            AtmLocation.getRandomLocation()
                    );
                    transactionDao.addTransaction(transaction);

                    return true;
                }

                @Override
                protected void done() {
                    loading.dispose();
                    try {
                        if (get()) {
                            JOptionPane.showMessageDialog(panelCash, transactionTypeString + " exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            goBackToDashboard();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panelCash, "Ocurrió un error durante la transacción.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            };

            worker.execute();
            loading.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panelCash, "Formato de monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBackToDashboard() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCash);
        frame.dispose();
        new Dashboard(card).showUI();
    }

    public void showUI() {
        JFrame frame = new JFrame(transactionTypeString);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(panelCash);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}