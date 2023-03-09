package atto.repository;
import atto.database.Database;
import atto.dto.Card;
import atto.dto.Terminal;
import atto.enums.TerminalStatus;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TerminalRepository {

    public  Terminal getTerminalByCode(String code) {
        try {
            Terminal terminal = null;
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from terminal where code = ?");
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                terminal = new Terminal();
                terminal.setId(resultSet.getInt("id"));
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
                terminal.setStatus(TerminalStatus.valueOf(resultSet.getString("status")));
                terminal.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
            }
            connection.close();
            return terminal;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveTerminal(Terminal terminal) {
        try {
            Connection connection = Database.getConnection();
            //String sql = String.format("insert into card(number,created_date,exp_date)values('%s',now(),'%s')", card.getNumber(), card.getExp_date());
            PreparedStatement preparedStatement = connection.prepareStatement("insert into terminal(code,create_date,address)values(?,now(),?)");
            preparedStatement.setString(1, terminal.getCode());
            preparedStatement.setString(2, terminal.getAddress());
            int i = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Terminal> getAll() {
        List<Terminal> terminalList = new LinkedList<>();
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from terminal");
            while (resultSet.next()) {

                Terminal terminal = new Terminal();
                terminal.setId(resultSet.getInt("id"));
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
                terminal.setStatus(TerminalStatus.valueOf(resultSet.getString("status")));
                terminal.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime());
                terminalList.add(terminal);
            }
            connection.close();
            return terminalList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return terminalList;
    }

    public void updateTerminal(Terminal terminal) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update terminal set address = ?  where code = ?");
            preparedStatement.setString(1, terminal.getAddress());
            preparedStatement.setString(2, terminal.getCode());
            int i = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Card deleteTerminal(String code) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from terminal where code = ?");
            preparedStatement.setString(1, code);
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCardByStatus(TerminalStatus status, String code) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update terminal set status = ?  where code = ?");
            preparedStatement.setString(1, status.name());
            preparedStatement.setString(2, code);
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            System.out.println("Terminal status is update successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
