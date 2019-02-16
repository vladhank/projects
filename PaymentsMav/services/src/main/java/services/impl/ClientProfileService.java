package services.impl;

import dao.impl.ClientProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojos.ClientProfile;
import services.BaseService;
import services.IClientProfileService;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientProfileService extends BaseService<ClientProfile> implements IClientProfileService {
    @Autowired
    ClientProfileDao clientProfileDao;
}
