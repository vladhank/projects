package services;

import entities.Client;

import java.io.Serializable;
import java.util.List;

public interface ClientService {
    Client save(Client client);

    Client get(Serializable id);

    void update(Client client);

    int delete(Serializable id);

    Client getAuthorizationInfo(String login);

    List<Client> getAll();
}
