package dao.Impl;

import dao.ClientDAO;
import databaseConnection.ConnectionPool;
import entities.Client;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

//TODO в пределах dao метода создается новый connection,statement,resultset и закрывать в обратном порядке
public class ClientDAOImpl extends AbstractDAO implements ClientDAO {
    public final static Logger logger = Logger.getLogger(ClientDAOImpl.class);
    private static volatile ClientDAO INSTANCE = null;
    private static final String saveClientQuery = "INSERT  INTO CLIENT (FIRST_NAME,LAST_NAME, ADDRESS,DATE_OF_BIRTH,PHONE_NUMBER,LOGIN,PASSWORD) VALUES (?,?,?,?,?,?,?)";
    private static final String updateClientQuery = "UPDATE CLIENT SET FIRST_NAME=?,LAST_NAME=?, ADDRESS=?,DATE_OF_BIRTH=?,PHONE_NUMBER=?,LOGIN=?,PASSWORD=? WHERE CLIENT_ID=?";
    private static final String getClientQuery = "SELECT * FROM CLIENT WHERE CLIENT_ID=?";
    private static final String getAllClientQuery = "SELECT * FROM CLIENT";
    private static final String getAuthorizationInfoQuery = "SELECT * FROM CLIENT WHERE LOGIN=?";
    private static final String deleteClientQuery = "DELETE FROM CLIENT WHERE CLIENT_ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetAuthorization;
    private PreparedStatement psDelete;

    {
        try {
            psSave = ConnectionPool.getConnection().prepareStatement(saveClientQuery, Statement.RETURN_GENERATED_KEYS);
            psUpdate = ConnectionPool.getConnection().prepareStatement(updateClientQuery);
            psGet = ConnectionPool.getConnection().prepareStatement(getClientQuery);
            psGetAll = ConnectionPool.getConnection().prepareStatement(getAllClientQuery);
            psGetAuthorization = ConnectionPool.getConnection().prepareStatement(getAuthorizationInfoQuery);
            psDelete = ConnectionPool.getConnection().prepareStatement(deleteClientQuery);
        } catch (SQLException ex) {
            logger.error("Exception while creating connection for prepared statement", ex);
            ex.printStackTrace();
        }
    }

    public ClientDAOImpl() {
    }

    public static ClientDAO getInstance() {
        ClientDAO clientDAO = INSTANCE;
        if (clientDAO == null) {
            synchronized (ClientDAOImpl.class) {
                clientDAO = INSTANCE;
                if (clientDAO == null) {
                    INSTANCE = clientDAO = new ClientDAOImpl();
                }
            }
        }
        return clientDAO;
    }

    @Override
    public Client save(Client client) throws SQLException {
        psSave.setString(1, client.getFirstName());
        psSave.setString(2, client.getLastName());
        psSave.setString(3, client.getAddress());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateFormat.format(client.getDateOfBirth());
        java.sql.Date sqlDate = java.sql.Date.valueOf(stringDate);
        psSave.setDate(4, sqlDate);
        psSave.setString(5, client.getPhoneNumber());
        //psSave.setLong(6, client.getCardNumber());
        psSave.setString(6, client.getLogin());
        psSave.setString(7, client.getPassword());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            client.setClientID(rs.getLong(1));
        }
        close(rs);
        return client;

    }


    void method(int a, double d, char... chars) {
    }

    @Override
    public Client get(Serializable ClientID) throws SQLException {
        psGet.setLong(1, (long) ClientID);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            Client client = new Client();
            client.setClientID(rs.getLong(1));
            client.setFirstName(rs.getString(2));
            client.setLastName(rs.getString(3));
            client.setAddress(rs.getString(4));
            client.setDateOfBirth(rs.getDate(5));
            client.setPhoneNumber(rs.getString(6));
//            client.setCardNumber(rs.getLong(7));
            client.setLogin(rs.getString(7));
            client.setPassword(rs.getString(8));
            return client;
        }
        close(rs);
        return null;
    }

    @Override
    public void update(Client client) throws SQLException {
//        psUpdate=prepareStatement()
        psUpdate.setLong(8, client.getClientID());
        psUpdate.setString(1, client.getFirstName());
        psUpdate.setString(2, client.getLastName());
        psUpdate.setString(3, client.getAddress());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateFormat.format(client.getDateOfBirth());
        java.sql.Date sqlDate = java.sql.Date.valueOf(stringDate);
        psUpdate.setDate(4, sqlDate);
        psUpdate.setString(5, client.getPhoneNumber());
        // psUpdate.setLong(6, client.getCardNumber());
        psUpdate.setString(6, client.getLogin());
        psUpdate.setString(7, client.getPassword());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable clientID) throws SQLException {
        psDelete.setLong(1, (long) clientID);
        return psDelete.executeUpdate();
    }

    @Override
        public Client getAuthorizationInfo(String login) throws SQLException {
        psGetAuthorization.setString(1,login);
        psGetAuthorization.executeQuery();
        ResultSet rs= psGetAuthorization.getResultSet();
        Client client = new Client();
        while(rs.next()){
            client.setClientID(rs.getLong(1));
            client.setFirstName(rs.getString(2));
            client.setLastName(rs.getString(3));
            client.setAddress(rs.getString(4));
            client.setDateOfBirth(rs.getDate(5));
            client.setPhoneNumber(rs.getString(6));
            client.setLogin(rs.getString(7));
            client.setPassword(rs.getString(8));
        }
        close(rs);
       return client;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> list = new ArrayList<>();
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            Client client = new Client();
            client.setClientID(rs.getLong(1));
            client.setFirstName(rs.getString(2));
            client.setLastName(rs.getString(3));
            client.setAddress(rs.getString(4));
            client.setDateOfBirth(rs.getDate(5));
            client.setPhoneNumber(rs.getString(6));
            //client.setCardNumber(rs.getLong(7));
            client.setLogin(rs.getString(7));
            client.setPassword(rs.getString(8));
            list.add(client);
        }
        close(rs);
        return list;
    }



}
