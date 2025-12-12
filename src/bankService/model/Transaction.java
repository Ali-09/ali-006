package bankService.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private TransactionType type; // e.g. "WITHDRAWAL", "DEPOSIT"
    private double amount;
    private AtmLocation atmLocation;
    private String description;
    private LocalDateTime date;
    private int cardId;

    public Transaction(int cardId, TransactionType type, double amount, AtmLocation atmLocation) {
        this.cardId = cardId;
        this.type = type;
        this.amount = amount;
        this.atmLocation = atmLocation;
        this.date = LocalDateTime.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public AtmLocation getAtmLocation() { return atmLocation; }
    public void setAtmLocation(AtmLocation atmLocation) { this.atmLocation = atmLocation; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public int getCardId() { return cardId; }

    public void setCardId(int cardId) { this.cardId = cardId; }
}
