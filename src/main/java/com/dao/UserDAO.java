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

    public UserEntity getUser(String email){
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserEntity where email = :email");
        query.setParameter("email",email);
        UserEntity user = (UserEntity) query.uniqueResult();
        session.getTransaction().commit();
        return user;
    }
    public void addUser(RegistrationBean userBean){
        UserEntity user = convertToUser(userBean);
        UserEntity testUser = getUser(user.getEmail());
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
        UserEntity user = getUser(email);
        if (user !=null && user.getPassword().equals(password)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkUser(String email){
        UserEntity user = getUser(email);
        if (user !=null){
        return true;
        }
        else {
            return false;
        }
    }

    private UserEntity convertToUser(RegistrationBean registrationBean) {
        UserEntity user = new UserEntity();
        user.setName(registrationBean.getName());
        user.setLastName(registrationBean.getLastName());
        user.setEmail(registrationBean.getEmail());
        user.setPassword(registrationBean.getPassword());
        return user;
    }

}
