package dao.impl;

import dao.BaseDao;
import dao.IClientProfileDao;
import org.springframework.stereotype.Repository;
import pojos.ClientProfile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientProfileDao extends BaseDao<ClientProfile> implements IClientProfileDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<ClientProfile> getClientProfiles(){
        List<ClientProfile> clientProfileList = entityManager.createQuery("FROM ClientProfile ").getResultList();
        return clientProfileList;
    }

}
