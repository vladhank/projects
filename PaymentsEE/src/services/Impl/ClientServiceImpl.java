package services.Impl;

import dao.ClientDAO;
import dao.Impl.ClientDAOImpl;
import entities.Client;
import org.apache.log4j.Logger;
import services.ClientService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ClientServiceImpl extends AbstractService implements ClientService {
    private ClientDAO clientDAO = ClientDAOImpl.getInstance();
    private static volatile ClientService INSTANCE = null;
    public final static Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Override
    public Client save(Client client) {
        try {
            startTransaction();
            client = clientDAO.save(client);
            commit();
        } catch (SQLException ex) {
            rollback();
            logger.error("Error creating Client"+ex);
            throw new ServiceException("Error creating Client" + client+ex);

        }
        return client;
    }

    @Override
    public Client get(Serializable id) {
        try {
            return clientDAO.get(id);
        } catch (SQLException ex) {
            logger.error("Error getting Client by ID");
            throw new ServiceException("Error getting Client by ID" + id);
        } catch (ParseException e) {
            logger.error("Error while parsing date from SQL to Java");
            throw new ServiceException("Error while parsing date from SQL to Java" + id);
        }
    }

    @Override
    public void update(Client client) {
        try {
            startTransaction();
            clientDAO.update(client);
            commit();
        } catch (SQLException e) {
            rollback();
            logger.error("Error updating Client");
            throw new ServiceException("Error updating Client" + client);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return clientDAO.delete(id);
        } catch (SQLException e) {
            rollback();
            logger.error("Can't delete client with ID " + id + "reason: " + e);
            throw new ServiceException("Can't delete client with ID " + id + "reason: " + e);
        }
    }

    @Override
    public Client getAuthorizationInfo(String login) {
        try {
            return clientDAO.getAuthorizationInfo(login);
        } catch (SQLException ex) {
            logger.error("Error while getting authentication info");
            throw new ServiceException("Error while getting authentication info" + clientDAO);
        }
    }

    @Override
    public List<Client> getAll() {

        try {
            startTransaction();
            List<Client> list = clientDAO.getAll();
            commit();
            return list;
        } catch (SQLException ex) {
            rollback();
            logger.error("Error while getting list of clients");
            throw new ServiceException("Error while getting list of clients");
        }
    }

    public static ClientService getInstance() {
        ClientService clientService = INSTANCE;
        if (clientService == null) {
            synchronized (ClientServiceImpl.class) {
                clientService = INSTANCE;
                if (clientService == null) {
                    INSTANCE = clientService = new ClientServiceImpl();
                }
            }
        }
        return clientService;
    }


}
