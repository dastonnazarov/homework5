package atto.repository;

import atto.database.Database;
import atto.dto.Profile;

import atto.enums.ProfileRole;
import atto.enums.ProfileStatus;
import atto.enums.TerminalStatus;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class ProfileRepository {

    public Profile getProfileByPhone(String phone) {

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
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime().toLocalDate());
            }
            connection.close();
            return profile;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveProfile(Profile profile) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into profile (name,surname,phone,password,create_date)values(?,?,?,?,now())");
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getSurname());
            preparedStatement.setString(3, profile.getPhone());
            preparedStatement.setString(4, profile.getPassword());
            int i = preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Profile> getAllProfile() {
        List<Profile> profileList = new LinkedList<>();
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Profile");
            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
                profile.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime().toLocalDate());
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setRole(ProfileRole.valueOf(resultSet.getString("role")));

                profileList.add(profile);
            }
            connection.close();
            return profileList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProfileByStatus(String status, String phone) {

        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update profile set status = ?  where phone = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, phone);
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            System.out.println("Profile status is update successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*    public Profile getProfileById(Integer id) {

            try {
                Profile profile = null;
                Connection connection = Database.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from profile where id = ?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    profile = new Profile();
                    profile.setId(resultSet.getInt("id"));
                    profile.setName(resultSet.getString("name"));
                    profile.setSurname(resultSet.getString("surname"));
                    profile.setPhone(resultSet.getString("phone"));
                    profile.setPassword(resultSet.getString("password"));
                    profile.setCard_number(resultSet.getString("card_number"));
                    profile.setCreate_date(resultSet.getTimestamp("create_date").toLocalDateTime().toLocalDate());
                }
                connection.close();
                return profile;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }*/
}