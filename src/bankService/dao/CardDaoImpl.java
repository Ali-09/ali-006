package bankService.dao;

import bankService.config.DatabaseConn;
import bankService.model.Card;
import bankService.model.Customer;

import java.sql.*;

public class CardDaoImpl implements CardDao {

    @Override
    public Card findByNumberCardAndPin(String numberCard, String pin) {
        Card card = new Card();

        String query = "SELECT c.card_id, c.card_number, c.linked_balance, " +
                "cust.customer_id, cust.first_name, cust.last_name " +
                "FROM card c " +
                "JOIN customer cust ON c.customer_id = cust.customer_id " +
                "WHERE c.card_number = ? AND cust.pin = ?";

        try (Connection conn = DatabaseConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, numberCard);
            stmt.setString(2, pin);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    card = new Card();
                    card.setId(rs.getInt("card_id"));
                    card.setNumberCard(rs.getString("card_number"));
                    card.setLinkedBalance(rs.getDouble("linked_balance"));

                    Customer cust = new Customer();
                    cust.setId(rs.getInt("customer_id"));
                    cust.setFirstName(rs.getString("first_name"));
                    cust.setLastName(rs.getString("last_name"));
                    cust.setPin(pin);

                    card.setCustomer(cust);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching card", e);
        }
        return card;
    }

    @Override
    public void updateBalance(int cardId, double newBalance) {
        String query = "UPDATE card SET linked_balance = ? WHERE card_id = ?";
        try (Connection conn = DatabaseConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, newBalance);
            stmt.setInt(2, cardId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating balance", e);
        }
    }
}
