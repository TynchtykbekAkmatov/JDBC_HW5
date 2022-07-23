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
        try(Statement statement = connection.createStatement()){
            System.out.println(statement.executeUpdate(createTable));
            System.out.println("Tablica koshuldu");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Tablica koshulgan jok!");
        }
    }

    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()){
            System.out.println(statement.executeUpdate("drop table if exists users"));
            System.out.println("Tablica ochtu");
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String save = "insert into users(name, lastName, age)" +
                "values (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(save)){
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

        String removeById = "delete from users where id = "+id;
        try(Statement statement = connection.createStatement()){

            statement.executeUpdate(removeById);
            System.out.println("Siz tandagan id ochtu");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Siz tandagan id ochkon jok!");
        }

    }

    public List<User> getAllUsers() {
            List<User> userList = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){

                userList.add(new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
            return userList;
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Chykpai kaldy");
            throw new RuntimeException();
        }
    }

    public void cleanUsersTable() {
        String cleanUsTable = "delete from users";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(cleanUsTable);
            System.out.println("Tazalandy!");
        }catch (SQLException e){
            e.getMessage();
            System.out.println("Tazalangan jok");
        }

    }
}