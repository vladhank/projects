package dao;

import pojos.Client;

import java.util.List;

public interface IClientDao extends Dao<Client> {

    List<Client> getClients();

    Client findByLogin(String login);

}
