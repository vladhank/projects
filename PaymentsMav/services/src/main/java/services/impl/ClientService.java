package services.impl;

import dao.IClientDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Client;
import services.BaseService;
import services.IClientProfileService;
import services.IClientService;

import java.util.List;

@Service
@Transactional
public class ClientService extends BaseService<Client> implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Autowired
    private IClientProfileService clientProfileService;


//    public Client add(Client client){
//        client.getClientProfiles().add(clientProfileService.get(1L));
//        return clientDao.add(client);
//    }

    public Client addAdmin(Client client) {
        client.getClientProfiles().add(clientProfileService.get(2L));
        return clientDao.add(client);
    }


    public List<Client> getClients() {
        return clientDao.getClients();
    }

    public Client findByLogin(String login) {
        return clientDao.findByLogin(login);
    }


}
