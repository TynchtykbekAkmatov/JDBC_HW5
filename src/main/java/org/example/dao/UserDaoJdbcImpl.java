package org.example.dao;

import org.example.model.User;
import org.example.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao{
    private static final Connection connection = Util.getConnection();
    private Util util = new Util();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String createTable = "create table if not exists users(" +
                "id serial primary key," +
                "name varchar," +
                "lastName varchar," +
                "age int)";
        try(Connection conn = connection;
            Statement statement = conn.createStatement()){
            System.out.println(statement.executeUpdate(createTable));
            System.out.println("Tablica koshuldu");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Tablica koshulgan jok!");
        }
    }

    public void dropUsersTable() {
        try(Connection conn = connection;
            Statement statement = conn.createStatement()){
            System.out.println(statement.executeUpdate("drop table if exists users"));
            System.out.println("Tablica ochtu");
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String removeById = "insert into users(name, lastName, age)" +
                "values (?,?,?)";
        try(Connection conn = connection;
            PreparedStatement preparedStatement = conn.prepareStatement(removeById)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            System.out.println(preparedStatement.executeUpdate());
            System.out.println(name+" tablicaga koshuldu");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("koshulgan jok!");
        }

    }

    public void removeUserById(long id) {

        String removeById = "delete from users where id = ?";
        try(Connection conn = connection;
            PreparedStatement preparedStatement = conn.prepareStatement(removeById)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Siz tandagan id ochtu");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Siz tandagan id ochkon jok!");
        }

    }

    public List<User> getAllUsers() {
        try(Connection conn = connection;
            Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            List<User> userList = new ArrayList<>();
            while (resultSet.next()){
                userList.add(new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
            return userList;
        }catch (SQLException e){
            e.getMessage();
            System.out.println();            throw new RuntimeException();
        }
    }

    public void cleanUsersTable() {
        String cleanUsTable = "truncate table users";
        try (Connection conn = connection;
             Statement statement = connection.createStatement()){
            statement.executeUpdate(cleanUsTable);
            System.out.println("Tazalandy!");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Tazalangan jok");
        }

    }
}