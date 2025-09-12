package bankService.model;

public class Card {
    private int id;
    private String numberCard;
    private String expireCard;
    private double linkedBalance;

    private Customer customer; // relación con el cliente

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumberCard() { return numberCard; }
    public void setNumberCard(String numberCard) { this.numberCard = numberCard; }

    public String getExpireCard() { return expireCard; }
    public void setExpireCard(String expireCard) { this.expireCard = expireCard; }

    public double getLinkedBalance() { return linkedBalance; }
    public void setLinkedBalance(double linkedBalance) { this.linkedBalance = linkedBalance; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public boolean isInitialized() {
        return this.id > 0 && this.numberCard != null;
    }
}
