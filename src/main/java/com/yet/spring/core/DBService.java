package com.yet.spring.core;


import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getH2Connection();
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

        public static Connection getMysqlConnection () {
            try {
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

                StringBuilder url = new StringBuilder();

                url.
                        append("jdbc:mysql://").        //db type
                        append("localhost:").           //host name
                        append("3306/").                //port
                        append("mydb");                 //db name

                System.out.println("URL: " + url + "\n");

                Connection connection = DriverManager.getConnection(url.toString());
                return connection;
            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

    public static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "root";
            String pass = "root";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}