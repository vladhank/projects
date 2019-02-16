package dao.impl;

import dao.BaseDao;
import dao.IClientDao;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import pojos.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ClientDao extends BaseDao<Client> implements IClientDao {

    @PersistenceContext
    EntityManager entityManager;


    public List<Client> getClients() {
        String getAll = "FROM Client";
        Query query = entityManager.createQuery(getAll);
        return query.getResultList();
    }

    public Client findByLogin(String login) {

        return ( Client ) entityManager.createQuery("FROM Client c WHERE c.login=:login").setParameter("login",login).getSingleResult();

    }



//    public Client findByLogin(String login) {
//
//        Query query = entityManager.createQuery("FROM Client c WHERE c.login=:login");
//        query.setParameter("login", login);
////        return ( Client ) query.getSingleResult();
//        return ( Client ) entityManager.createQuery("FROM Client c WHERE c.login=:login").setParameter("login",login).getSingleResult();
//
//    }
}
