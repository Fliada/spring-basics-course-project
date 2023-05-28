package com.yet.spring.core;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

        public void printConnectInfo () {
            try {
                System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
                System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
                System.out.println("Driver: " + connection.getMetaData().getDriverName());
                System.out.println("Autocommit: " + connection.getAutoCommit());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @SuppressWarnings("UnusedDeclaration")
        public static Connection getMysqlConnection () {
            try {
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

                StringBuilder url = new StringBuilder();

                url.
                        append("jdbc:mysql://").        //db type
                        append("localhost:").           //host name
                        append("3306/").                //port
                        append("usersdb");       //db name

                System.out.println("URL: " + url + "\n");

                Connection connection = DriverManager.getConnection(url.toString(), "root", "root");
                return connection;
            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
}