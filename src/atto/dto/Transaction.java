package atto.dto;

import atto.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private Integer id;
    private Integer card_number;
    private String terminal_code;
    private Double amount;
    private TransactionType type;
    private LocalDateTime create_date;


    public Transaction() {
    }


    public Transaction(Integer id, Integer card_number, String terminal_code, Double amount, TransactionType type, LocalDateTime create_date) {
        this.id = id;
        this.card_number = card_number;
        this.terminal_code = terminal_code;
        this.amount = amount;
        this.type = type;
        this.create_date = create_date;
    }

    public Transaction(String number, Double balance, String code, TransactionType refill, LocalDateTime now) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCard_number() {
        return card_number;
    }

    public void setCard_number(Integer card_number) {
        this.card_number = card_number;
    }

    public String getTerminal_code() {
        return terminal_code;
    }

    public void setTerminal_code(String terminal_code) {
        this.terminal_code = terminal_code;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", card_number=" + card_number +
                ", terminal_code='" + terminal_code + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", create_date=" + create_date +
                '}';
    }
}
