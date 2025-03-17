package ru.albert.spring.dao;

import org.springframework.stereotype.Component;
import ru.albert.spring.models.Person;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_ID = 0;

    private static final String  URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();

        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM person";
        statement.executeQuery(SQL);
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            people.add(person);
        }
        return people;
    }

    public Person show(int id) throws SQLException {
        Person person = null;
       PreparedStatement statement =
               connection.prepareStatement("select * from Person where id=?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }

    public void save(Person person) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO person VALUES (1, ?, ?, ?)");
        statement.setString(1, person.getName());
        statement.setInt(2, person.getId());
        statement.setString(3, person.getEmail());
        statement.executeUpdate();
    }

    public void update(int id, Person updatedPerson) throws SQLException {
       PreparedStatement statement = connection.prepareStatement("update Person SET name=?, age=?, email=? WHERE id=?");
        statement.setString(1, updatedPerson.getName());
        statement.setInt(2, updatedPerson.getId());
        statement.setString(3, updatedPerson.getEmail());
        statement.setInt(4, updatedPerson.getId());
        statement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Person WHERE id=?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
