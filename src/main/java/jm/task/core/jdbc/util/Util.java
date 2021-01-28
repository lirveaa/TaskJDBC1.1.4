package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import javax.security.auth.login.AppConfigurationEntry;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static  SessionFactory sessionFactory;

    private static StandardServiceRegistry standardServiceRegistry;
    private  static Session session;

    Connection connection = null;
    Driver driver;



    public Util(){

        try {
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


    }

    public  Session getSession() throws HibernateException{
        return sessionFactory.openSession();
    }

    public SessionFactory getSessionFactory(){
             if(sessionFactory==null){
                 try {
                     Configuration configuration = new Configuration();
                     Properties settings = new Properties();
                     settings.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
                     settings.put(Environment.URL, URL);
                     settings.put(Environment.USER, "root");
                     settings.put(Environment.PASS, "root");
                     settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                     settings.put(Environment.SHOW_SQL, "true");

                     settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                     settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                     configuration.setProperties(settings);

                     configuration.addAnnotatedClass(User.class);

                     ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                             .applySettings(configuration.getProperties()).build();

                     sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                 } catch (Exception e){
                     e.printStackTrace();
                 }
             }
             return sessionFactory;
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

}
