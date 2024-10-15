package com.jetbrains.marco;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

public class JdbcTutorial {

    private static final String H2_URL = "jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql'";

    public static void main(String[] args) {
        try {
            runCRUDOperations(getConnectionWithDriverManager());
            runCRUDOperations(getConnectionWithHikari());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnectionWithDriverManager() throws SQLException {
        return DriverManager.getConnection(H2_URL);
    }

    private static Connection getConnectionWithHikari() throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(H2_URL);
        return ds.getConnection();
    }

    private static void runCRUDOperations(Connection connection) throws SQLException {

        System.out.println("connection.isValid(0) = " + connection.isValid(0));

        // CRUD

        // select

        PreparedStatement ps = connection.prepareStatement("select * from USERS where name = ?");
        ps.setString(1, "Marco");

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name"));
        }

        // inserts

        PreparedStatement insertPs = connection.prepareStatement("insert into USERS (name) values (?)");
        insertPs.setString(1, "John");
        int insertCount = insertPs.executeUpdate();
        System.out.println("insertCount = " + insertCount);

        // updates

        PreparedStatement updatePs = connection.prepareStatement("update USERS set name = ? where name = ?");
        updatePs.setString(1, "Johnny");
        updatePs.setString(2, "John");
        int updateCount = updatePs.executeUpdate();
        System.out.println("updateCount = " + updateCount);

        // deletes

        PreparedStatement deletePs = connection.prepareStatement("delete from USERS where name = ?");
        deletePs.setString(1, "Johnny");
        int deleteCount = deletePs.executeUpdate();
        System.out.println("deleteCount = " + deleteCount);

    }

}