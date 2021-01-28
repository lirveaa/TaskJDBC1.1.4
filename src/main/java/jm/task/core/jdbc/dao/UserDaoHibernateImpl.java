package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    Util worker = new Util();
    User user = new User();
    private final String create_SQL ="CREATE TABLE IF NOT EXISTS User(id int not null AUTO_INCREMENT,name varchar(64), lastName varchar(64),age int, primary key(id))";


    @Override
    public void createUsersTable() {
        try {
            //String sql = create_SQL;
            Session session  = worker.getSessionFactory().openSession();
            session.createSQLQuery(create_SQL).executeUpdate();

        } catch (HibernateException e){
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            String sql = "DROP TABLE IF EXISTS user";
            Session session  = worker.getSession();
            session.createSQLQuery(sql).executeUpdate();

        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {


        try {
            Session session = worker.getSession();
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setAge(age);
            user.setLastName(lastName);
            session.save(user);
            transaction.commit();
            session.close();

        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = worker.getSession();
            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> item = new ArrayList<>();
        try {

            Session session  = worker.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            item = (List<User>)session.createQuery("from User").list();
            return item;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void cleanUsersTable() {

        try {
            Session session = worker.getSession();
            String sql_query = "DELETE FROM User";
            session.createSQLQuery(sql_query).executeUpdate();

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }
}
