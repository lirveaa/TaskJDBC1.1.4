package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
        User user = new User();
        ud.createUsersTable();
        ud.saveUser("Ivan", "Popov", (byte) 21);
        System.out.println(ud.getAllUsers());
        ud.cleanUsersTable();
        ud.dropUsersTable();


    }

}
