package atto.repository;

import atto.container.ComponentContainer;
import atto.database.Database;
import atto.dto.Card;
import atto.dto.Profile;
import atto.dto.Transaction;
import atto.enums.CardStatus;
import atto.enums.TransactionType;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class CardRepository {


    public void saveCard(Card card) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into card(number,create_date,exp_date)values(?,now(),?)");
            preparedStatement.setString(1, card.getNumber());
            preparedStatement.setString(2, card.getExp_date());
            int i = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Card> getAll() {
        List<Card> contactList = new LinkedList<>();
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from card");
            while (resultSet.next()) {
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setBalance(resultSet.getDouble("balance"));
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                card.setPhone(resultSet.getString("phone"));
                card.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime().toLocalDate());
                card.setExp_date(resultSet.getString("exp_date"));
                contactList.add(card);
            }
            connection.close();
            return contactList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public Card getCardByNumber(String number) {
        try {
            Card card = null;
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from card where number = ?");
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setBalance(resultSet.getDouble("balance"));
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                card.setPhone(resultSet.getString("phone"));
                card.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime().toLocalDate());
                card.setExp_date(resultSet.getString("exp_date"));

            }
            connection.close();
            return card;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Card getCardById(Integer id) {
        try {
            Connection connection = Database.getConnection();
            String sql = "select * from card where visible = true and  id = '" + id + "';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Integer cardId = resultSet.getInt("id");
                String cardNumber = resultSet.getString("number");
                Double balance = resultSet.getDouble("balance");
                String expDate = resultSet.getString("exp_date");
                String status = resultSet.getString("status");
                String phone = resultSet.getString("phone");
                LocalDate createdDate = resultSet.getTimestamp("create_date").toLocalDateTime().toLocalDate();

                Card card = new Card();
                card.setId(cardId);
                card.setNumber(cardNumber);
                card.setBalance(balance);
                card.setExp_date(expDate);
                card.setStatus(CardStatus.valueOf(status));
                card.setPhone(phone);
                card.setCreate_date(createdDate);
                return card;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public void updateCard(Card card) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update card set exp_date = ?  where number = ?");
            preparedStatement.setString(1, card.getExp_date());
            preparedStatement.setString(2, card.getNumber());
            int i = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Card deleteCard(String number) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from card where number = ?");
            preparedStatement.setString(1, number);
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCardByStatus(CardStatus status , String number) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update card set status = ?  where number = ?");
            preparedStatement.setString(1, status.name());
            preparedStatement.setString(2, number);
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            System.out.println("Card status is update successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Card> cardListByPhone(Profile profile) {
        return getAll().stream().filter(card -> card.getPhone()!=null && card.getPhone().equals(profile.getPhone())).collect(Collectors.toList());
    }

    public void refill(String number,String code, Double balance,Card card ) {
        Connection con = Database.getConnection();
        String sql = String.format("update card set balance = '%s' where number = '%s'", balance+card.getBalance(), number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Balance Success Updated !!!");
                ComponentContainer.transactionService.addTransaction(new Transaction(number,balance,code, TransactionType.REFILL, LocalDateTime.now()));
            } else {
                System.out.println("Balance Not Updated");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void makePayment(String number,String code, Double balance,Card card){
        Connection con = Database.getConnection();
        String sql = String.format("update card set balance = '%s' where number = '%s'", card.getBalance()-balance, number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                generalBalance(ComponentContainer.generalBalance,balance);
                System.err.println("Balance Success Updated !!!");
                ComponentContainer.transactionService.addTransaction(new Transaction(number,balance,code, TransactionType.REFILL,LocalDateTime.now()));
            } else {
                System.out.println("Balance Not Updated");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void generalBalance(String number,Double balance){
        Connection con = Database.getConnection();
        String sql = String.format("update card set balance = '%s' where number = '%s'", getCard(number).getBalance()+balance, number);
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            int b = statement.executeUpdate();
            if (b > 0) {
                System.err.println("Balance Success Updated !!!");
            } else {
                System.out.println("Balance Not Updated");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void companyCardBalance() {
        System.out.println(getCard(ComponentContainer.generalBalance).getBalance());

    }

    private Card getCard(String number) {
        String sql = String.format("select * from card where number ='%s'", number);
        Card card = new Card();
        Connection con = Database.getConnection();
        try {
            ResultSet resultSet = con.prepareStatement(sql).executeQuery();
            if (resultSet.next()) {
                card.setPhone(resultSet.getString("phone"));
                card.setNumber(number);
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                card.setBalance(resultSet.getDouble("balance"));
                card.setExp_date(resultSet.getString("exp_date"));
                card.setCreate_date(resultSet.getTimestamp("created_date").toLocalDateTime().toLocalDate());
            }
            con.close();
            return card;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void reFill(String cardNumber, double v) {

    }
}
