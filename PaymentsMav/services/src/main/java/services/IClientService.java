package services;

import pojos.Client;

import java.util.List;

public interface IClientService extends IService<Client> {

    List<Client> getClients();

    Client findByLogin(String login);
}
