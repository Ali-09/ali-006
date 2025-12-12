package bankService.dao;

import bankService.config.DatabaseConn;
import bankService.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO public.atm_transaction (card_id, type, amount, atm_location, executed_at) VALUES (?, ?::transaction_type, ?, ?, ?)";

        try (Connection conn = DatabaseConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transaction.getCardId());
            pstmt.setString(2, transaction.getType().name());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getAtmLocation().getDisplayName());
            pstmt.setTimestamp(5, Timestamp.valueOf(transaction.getDate()));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}