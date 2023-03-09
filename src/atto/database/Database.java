package atto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


//991155204
//991155204
public class Database {


    public static void createProfileTable() {
        try {
            String profileTable = "create table if not exists profile( " +
                    "                id serial primary key, " +
                    "                name varchar(25) not null, " +
                    "                surname varchar(25) not null, " +
                    "                phone varchar(13) , " +
                    "                password varchar(10) not null," +
                    "                create_date timestamp," +
                    "                status varchar default 'BLOCK'," +
                    "                role varchar default 'USER'" +
                    "         );";
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(profileTable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createCardTable() {
        try {
            String profileTable = "create table if not exists card( " +
                    "                id serial primary key, " +
                    "                number varchar(16) not null, " +
                    "                balance real  default '0', " +
                    "                status varchar default 'BLOCK', " +
                    "                phone varchar(13)," +
                    "                create_date timestamp," +
                    "                exp_date varchar not null," +
                    "                added_date timestamp ," +
                    "                visible boolean default true" +
                    "         );";
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(profileTable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTerminalTable() {
        try {
            String profileTable = "create table if not exists terminal( " +
                    "                id serial primary key, " +
                    "                code varchar not null, " +
                    "                address varchar  not null, " +
                    "                status varchar default 'BLOCK', " +
                    "                create_date timestamp" +
                    "         );";

            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(profileTable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTransactionTable() {
        try {
            String profileTable = "create table if not exists transaction( " +
                    "                id serial primary key, " +
                    "                card_id Integer not null, " +
                    "                terminal_id Integer, " +
                    "                amount real ," +
                    "                type varchar ," +
                    "                create_date timestamp default now()," +
                    "foreign key(card_id) references card(id)," +
                    "foreign key(terminal_id) references terminal(id));";
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(profileTable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void initAdmins() {
        try {
            String profileTable = "insert into profile(id,name,surname,phone,password,create_date,status,role) " +
                    "values('-1','admin','adminov','1111','123',now(),'ACTIVE','ADMIN') ON CONFLICT (id) DO NOTHING;";
            Connection con = getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(profileTable);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            // 1
            Class.forName("org.postgresql.Driver");
            // 2-yo'l.
            return DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_atto", "postgres", "123");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
