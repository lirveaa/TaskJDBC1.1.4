package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl()   {

    }
    Util worker = new Util();
    User user = new User();

    public void createUsersTable() {

        try{
            Statement statement = worker.getConnection().createStatement();
            PreparedStatement create = worker.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS User(id int not null AUTO_INCREMENT,name varchar(64), lastName varchar(64),age int, primary key(id))");
            create.executeUpdate();
            System.out.println("Created a table!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {

            String sql = "DROP TABLE IF EXISTS user";
            //statement.executeUpdate(sql);

            PreparedStatement drop = worker.getConnection().prepareStatement(sql);
            drop.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO user(name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = worker.getConnection().prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.addBatch(sql);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
            try {
                Statement statement = worker.getConnection().createStatement();
                String sql = "DELETE * FROM user WHERE id="+id;
                statement.executeUpdate(sql);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    public List<User> getAllUsers()  {
        List<User> itemList = new ArrayList<>();

        try {
            Statement statement = worker.getConnection().createStatement();
            String sql = "SELECT * FROM  user";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                itemList.add(user);
                //System.out.println(itemList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemList;

    }

    public void cleanUsersTable() {
        String sql = "DELETE  FROM user";

        PreparedStatement statement1 = null;

        try {
            //assert false;
            statement1 = worker.getConnection().prepareStatement(sql);
            statement1.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
