package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
public class UserServiceImpl implements UserService {
    UserService userDaoJDBC = new UserService() {
        @Override
        public void createUsersTable() {
            userDaoJDBC.createUsersTable();
        }

        @Override
        public void dropUsersTable() {
            userDaoJDBC.dropUsersTable();
        }

        @Override
        public void saveUser(String name, String lastName, byte age) {
            userDaoJDBC.saveUser(name,lastName,age);
        }

        @Override
        public void removeUserById(long id) {
            userDaoJDBC.removeUserById(id);
        }

        @Override
        public List<User> getAllUsers() {
            return userDaoJDBC.getAllUsers();
        }

        @Override
        public void cleanUsersTable() {
            userDaoJDBC.cleanUsersTable();
        }
    };

    public UserServiceImpl()   {
    }

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
            userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }
}
