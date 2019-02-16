package dao;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
@Repository
public class BaseDao<T> implements Dao<T> {

    private static Logger log = Logger.getLogger(BaseDao.class);

    Class<T> clazz;

    @PersistenceContext
    @Getter
    private EntityManager entityManager;

    public T add(T t) {
        entityManager.persist(t);
        log.info("Save:" + t);
        return t;
    }


    public T update(T t) {
        entityManager.merge(t);
        log.info("Update:" + t);
        return t;
    }

    public T get(Serializable id) {
        log.info("Get:" + id);
        return ( T ) entityManager.find(getPersistentClass(), id);
    }

    public void delete(T t) {
        log.info("Delete:" + t);
        entityManager.remove(t);
    }

    public void deleteById(Serializable id) {
        T entity = get(id);
        delete(entity);
    }

    public void refresh(T t) {
        log.info("Refresh:" + t);
        entityManager.refresh(t);
    }


    private Class getPersistentClass() {
        return ( Class<T> ) ( ( ParameterizedType ) getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
    }
}
