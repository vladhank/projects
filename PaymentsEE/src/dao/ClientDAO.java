package dao;

import entities.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO  extends DAO<Client> {
    Client getAuthorizationInfo(String login) throws SQLException;
    List<Client> getAll() throws SQLException;
}
