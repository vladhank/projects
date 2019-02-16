package services.auth;

import enums.State;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Client;
import pojos.ClientProfile;
import services.IClientService;

import java.util.ArrayList;
import java.util.List;

@Service("authService")
public class AuthenticationService implements UserDetailsService {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IClientService clientService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientService.findByLogin(login);
        logger.debug("Client ");
        System.out.println("Client : " + client);
        System.out.println();
        if (client == null) {
            System.out.println("Such client not found");
            throw new UsernameNotFoundException("Client not found");
        }
        return new MvcUser(client. getFirstName(), client.getLastName(), client.getLogin(), client.getPassword(),
                State.ACTIVE.getState().equals(client.getState()), true, true, true, getGrantedAuthorities(client));
    }


    private List<GrantedAuthority> getGrantedAuthorities(Client client) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (ClientProfile clientProfile : client.getClientProfiles()) {
            System.out.println("PersonProfile : " + clientProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + clientProfile.getType()));
        }
        System.out.print("authorities :" + authorities);
        return authorities;
    }

}
