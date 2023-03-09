package atto.repository;

import atto.database.Database;
import atto.dto.Card;
import atto.dto.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public Profile getUserByPhone(String phone) {

        try {
            Profile profile = null;
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from profile where phone = ?");
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime().toLocalDate());
            }
            connection.close();
            return profile;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveUserCard(Card card) {
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
}
