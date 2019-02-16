package pojos;

import enums.CardType;
import enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor


@Entity
@Table
public  class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Long clientID;

    @Column(name = "FIRST_NAME", nullable = false)
    @Pattern(regexp="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$|^[а-яА-я]+(([',. -][а-яА-Я ])?[а-яА-Я]*)*$", message="First name can only contain letters")
    @Size(min=2,max=10,message = "First name should have at least 2 characters ")
    private String firstName;

    @Pattern(regexp="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$|^[а-яА-я]+(([',. -][а-яА-Я ])?[а-яА-Я]*)*$",message ="Last name can only contain letters" )
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Pattern(regexp="^\\+(?:[0-9] ?){6,14}[0-9]$", message="Phone number should be in international format")
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "ADDRESS", nullable = false)
    @AttributeOverrides({
            @AttributeOverride(name = "city",column = @Column(name = "CITY",nullable=true)),
            @AttributeOverride(name = "street",column = @Column(name = "STREET",nullable=true)),
            @AttributeOverride(name = "house",column = @Column(name = "HOUSE",nullable=true)),
            @AttributeOverride(name = "apartment",column = @Column(name = "APARTMENT",nullable=true))
    })
    @Embedded
    private Address address;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @Past(message = "Only the past is valid")
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Access(AccessType.FIELD)
    @Column(name = "LOGIN", nullable = false, unique = true, insertable = true, updatable = false)
    @Email
    private String login;

    @Access(AccessType.PROPERTY)
    @Column(name = "PASSWORD", nullable = false)
//    @Max(value = 30, message = "Max password length should be less then 30 characters")
//    @Min(value = 6, message = "Min password length should be great then 6 characters")
    private String password;

    @Column(name="STATE", nullable=false)
    private String state = State.ACTIVE.getState();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Client_Client_Profile",
            joinColumns = { @JoinColumn(name = "CLIENT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CLIENT_PROFILE_ID")})
    private Set<ClientProfile> clientProfiles = new HashSet<ClientProfile>();


    @OneToMany(mappedBy = "client",orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CreditCard> cards;

//    TODO addBankAccount

    public Client(String firstName, String lastName, String phoneNumber, Address address, LocalDate dateOfBirth, String login, String password, String state, Set<ClientProfile> clientProfiles, List<CreditCard> cards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.password = password;
        this.state = state;
        this.clientProfiles = clientProfiles;
        this.cards = cards;
    }

    public Client(String firstName, String lastName, String phoneNumber, Address address, String dateOfBirth, String login, String password, String state, Set<ClientProfile> clientProfiles, List<CreditCard> cards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.login = login;
        this.password = password;
        this.state = state;
        this.clientProfiles = clientProfiles;
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", dateOfBirth=" + dateOfBirth +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = ( Client ) o;
        return Objects.equals(clientID, client.clientID) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(phoneNumber, client.phoneNumber) &&
                Objects.equals(address, client.address) &&
                Objects.equals(dateOfBirth, client.dateOfBirth) &&
                Objects.equals(login, client.login) &&
                Objects.equals(password, client.password) &&
                Objects.equals(state, client.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, firstName, lastName, phoneNumber, address, dateOfBirth, login, password, state);
    }
}
