package dao.daoImpl;


import dao.UserDao;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
//TODO как вообще мне понять что выполняется транзакционность?
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addUser(User application) {
        getCurrentSession().persist(application);
    }

    @Override
    public void deleteUser(int userId) {
        getCurrentSession().delete(getUserById(userId));
    }

    @Override
    public void updateUser(User application) {
        getCurrentSession().update(application);
    }

    @Override
    public List<User> getAllUsers() {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        return (List<User>) criteria.list();
    }

    @Override
    public User getUserById(int userId) {
        return (User) getCurrentSession().load(User.class, userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }
}
