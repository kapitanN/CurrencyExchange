package com.dao;

import com.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.validation.ValidationException;


/**
 * Created by nikita on 04.01.2017.
 */

public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public UsersEntity getUser(String email){
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from UsersEntity where email = :email");
        query.setParameter("email",email);
        UsersEntity user = (UsersEntity) query.uniqueResult();
        session.getTransaction().commit();
        return user;
    }
    public void addUser(RegistrationBean userBean){
        UsersEntity user = convertToUser(userBean);
        UsersEntity testUser = getUser(user.getEmail());
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        if (testUser != null){
            session.close();
            throw new ValidationException(String.format("User with email '%s' already exists", user.getEmail()));
        }
        session.save(user);
        session.getTransaction().commit();
    }

    public boolean checkUser(String email, String password){
        UsersEntity user = getUser(email);
        if (user !=null && user.getPassword().equals(password)){
            return true;
        }
        else {
            return false;
        }
    }

    private UsersEntity convertToUser(RegistrationBean registrationBean) {
        UsersEntity user = new UsersEntity();
        user.setName(registrationBean.getName());
        user.setLastName(registrationBean.getLastName());
        user.setEmail(registrationBean.getEmail());
        user.setPassword(registrationBean.getPassword());
        return user;
    }

}
