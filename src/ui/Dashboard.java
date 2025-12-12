package ui;

import bankService.dao.TransactionDao;
import bankService.dao.TransactionDaoImpl;
import bankService.model.AtmLocation;
import bankService.model.Card;
import bankService.model.TransactionType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {
    private JPanel panelDashboard;
    private JTextField textBalance;
    private JButton depositarButton;
    private JButton btnWithdraw;
    private JButton btnExit;
    private Card card;

    public Dashboard(Card card) {
        this.card = card;
        System.out.println("Dashboard View");
        System.out.println(card.getLinkedBalance());
        textBalance.setText(Double.toString(card.getLinkedBalance()));
        logBalanceInquiry();
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame dashboardFrame = (JFrame) SwingUtilities.getWindowAncestor(panelDashboard);
                dashboardFrame.dispose();
                new Login().showUI();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame dashboardFrame = (JFrame) SwingUtilities.getWindowAncestor(panelDashboard);
                dashboardFrame.dispose();
                new Login().showUI();
            }
        });

        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelDashboard);
                currentFrame.dispose();
                new Transaction(card, "Deposit").showUI();
            }
        });

        btnWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panelDashboard);
                currentFrame.dispose();
                new Transaction(card, "Withdraw").showUI();
            }
        });
    }

    private void logBalanceInquiry() {
        TransactionDao transactionDao = new TransactionDaoImpl();
        bankService.model.Transaction transaction = new bankService.model.Transaction(
                card.getId(),
                TransactionType.BALANCE_INQUIRY,
                card.getLinkedBalance(),
                AtmLocation.getRandomLocation()
        );
        transactionDao.addTransaction(transaction);
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