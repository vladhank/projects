package services.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.Collection;

public class MvcUser extends User implements Principal {
    private String firstName;
    private String lastName;
    private String displayName;

    public MvcUser(String firstName, String lastName, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        populateDisplayName();
    }

    public MvcUser(String firstName, String lastName, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        populateDisplayName();
    }

    public MvcUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MvcUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MvcUser mvcUser = (MvcUser) o;

        if (getFirstName() != null ? !getFirstName().equals(mvcUser.getFirstName()) : mvcUser.getFirstName() != null) return false;
        return getLastName() != null ? getLastName().equals(mvcUser.getLastName()) : mvcUser.getLastName() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }

    @Override
    public String getName() {
        return getUsername();
    }

    private void populateDisplayName() {
        String name = (firstName + " " + lastName).trim();
        displayName = !StringUtils.isBlank(name) ? name : getUsername();
    }
}
