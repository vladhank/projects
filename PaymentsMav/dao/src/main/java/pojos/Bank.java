package pojos;

import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode

@Entity
public class Bank implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankAccount_ID;

    @Column(name = "BANK_ACCOUNT")
    private String bankAccount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    @Column(name = "ACCOUNT_BALANCE", nullable = false)
    @Check(constraints = "BALANCE>=0")
    private double balance;

    @OneToMany (mappedBy = "bankAccount",orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<CreditCard> cardList;


    public Bank(String bankAccount, Client client, double balance, List<CreditCard> cardList) {
        this.bankAccount = bankAccount;
        this.client = client;
        this.balance = balance;
        this.cardList = cardList;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankAccount='" + bankAccount + '\'' +
                ", client=" + client +
                ", balance=" + balance +
                '}';
    }


}
