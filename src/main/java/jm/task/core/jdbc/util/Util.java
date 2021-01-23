package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    Connection connection = null;
    Driver driver;
    public Util(){

        try {
            //Class.forName(DRIVER);
            //Driver driver = new FabricMySQLDriver();
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            System.out.println("Driver success");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connection success");
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to connect!");
        }
//        finally {
//            try {
//                assert connection != null;
//                connection.close();
//                System.out.println("Connection closed");
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
    }
    public Connection getConnection() throws SQLException {
        return connection;
    }
}
