package com.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by nikita on 04.01.2017.
 */
public class HibernateSessionFactory {

    private static final Logger LOG = Logger.getLogger(HibernateSessionFactory.class);

    private static final String CONFIGURATION_LOADED = "Hibernate Configuration has been loaded.";
    private static final String REGISTRY_LOADED = "Hibernate Annotation Service Registry has been created.";
    private static final String CREATION_FAILED = "Initial Session Factory creation failed.";

    private static SessionFactory sessionFactory = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
        try {
            Configuration configuration = new Configuration().configure();
            LOG.info(CONFIGURATION_LOADED);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).build();
            LOG.info(REGISTRY_LOADED);
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Exception ex) {
            LOG.error(CREATION_FAILED);
            LOG.error(ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}