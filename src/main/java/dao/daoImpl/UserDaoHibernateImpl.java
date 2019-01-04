package dao.daoImpl;


import dao.UserDao;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.function.Consumer;

@Repository
@Transactional
//TODO не смог настроить транзакции через спринг. делал в ручную..
//TODO КАК? Если убираю ручные транзакции то при нажатии клавиш ничего не происходит
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private  EntityManagerFactory entityManagerFactory;
    private  EntityManager em;


    @Override
    public void addUser(User application) {
        em = entityManagerFactory.createEntityManager();
        executeInsideTransaction(entityManager -> entityManager.persist(application));

    }

    @Override
    public void deleteUser(int userId) {
       executeInsideTransaction(entityManager -> em.remove(getUserById(userId)));

    }

    @Override
    public void updateUser(User application) {
        executeInsideTransaction(entityManager -> em.merge(application));

    }

    @Override
    public List<User> getAllUsers() {
        em = entityManagerFactory.createEntityManager();

        return em.createQuery("FROM " + User.class.getName()).getResultList();

    }

    @Override
    public User getUserById(int userId) {
        return (User)em.find(User.class, userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
