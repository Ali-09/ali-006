package bankService.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private String type; // e.g. "WITHDRAWAL", "DEPOSIT"
    private double amount;
    private String atmLocation;
    private String description;
    private LocalDateTime date;

    private Card card; // relación con la tarjeta

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getAtmLocation() { return atmLocation; }
    public void setAtmLocation(String atmLocation) { this.atmLocation = atmLocation; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public Card getCard() { return card; }
    public void setCard(Card card) { this.card = card; }
}
