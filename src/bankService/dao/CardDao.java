package bankService.dao;

import bankService.model.Card;

public interface CardDao {
    Card findByNumberCardAndPin(String numberCard, String pin);
    void updateBalance(int cardId, double newBalance);
}