package dao;

import pojos.ClientProfile;

import java.util.List;

public interface IClientProfileDao extends Dao<ClientProfile> {

    List<ClientProfile> getClientProfiles();

}

