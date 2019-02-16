package pojos;

import com.sun.xml.bind.v2.TODO;
import enums.CardStatus;
import enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor


@Entity
public class CreditCard implements Serializable {
    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long cardID;

    @Id
    @Column(name = "CARD_NUMBER")
//    @Min(16)
//    @Max(16)
//    @Digits(integer=16,fraction = 0)
//    @Pattern(regexp = "^4\\d{15}|^5\\d{15}",message = "Card number should have at least 16 digits and no characters")
    private Long cardNumber;


    @Enumerated(EnumType.STRING)
    @Column(name = "CARD_COMPANY", nullable = false)
    private CardType cardCompany;

    @Column(name = "FIRST_NAME", nullable = false)
    @Pattern(regexp="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$|^[а-яА-я]+(([',. -][а-яА-Я ])?[а-яА-Я]*)*$", message="First name can only contain letters")
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @Pattern(regexp="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$|^[а-яА-я]+(([',. -][а-яА-Я ])?[а-яА-Я]*)*$",message ="Last name can only contain letters" )
    private String lastName;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "EXP_DATE", nullable = false)
    private LocalDate expDate;

    @Column(name = "CVV", nullable = false)
//    @Min(3)
//    @Max(3)
    private Integer cvv;

    @Column(name = "PIN", nullable = false)
//    @Min(4)
//    @Max(4)
    private Integer pin;

    @Column(name = "CASHBACK")
    private Integer cashBack;

    @Enumerated(EnumType.STRING)
    @Column(name = "CARD_STATUS", nullable = false)
//    @Pattern(regexp = "ACTIVE|DISABLED",message = "Card status can be ACTIVE or DISABLED")
    private CardStatus status;

    @ManyToOne
    @JoinColumn(name = "BANK_ACCOUNT", referencedColumnName = "BANK_ACCOUNT", nullable = true)
    private Bank bankAccount;

    @OneToMany(mappedBy = "creditCard", orphanRemoval = true)
    @Column(name = "TRANSACTIONS", nullable = true)
    private List<Transaction> transactionList;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID", nullable = false)
    private Client client;


    public CreditCard(Long cardNumber, CardType cardCompany, String firstName, String lastName, LocalDate expDate, Integer cvv, Integer pin, Integer cashBack, CardStatus status, Bank bankAccount, List<Transaction> transactionList, Client client) {
        this.cardNumber = cardNumber;
        this.cardCompany = cardCompany;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expDate = expDate;
        this.cvv = cvv;
        this.pin = pin;
        this.cashBack = cashBack;
        this.status = status;
        this.bankAccount = bankAccount;
        this.transactionList = transactionList;
        this.client = client;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", cardCompany='" + cardCompany + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", expDate=" + expDate +
                ", cvv=" + cvv +
                ", pin=" + pin +
                ", cashBack=" + cashBack +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = ( CreditCard ) o;
        return cardNumber == that.cardNumber &&
                cvv == that.cvv &&
                pin == that.pin &&
                cashBack == that.cashBack &&
                cardCompany == that.cardCompany &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(expDate, that.expDate) &&
                status == that.status &&
                Objects.equals(bankAccount, that.bankAccount) &&
                Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardCompany, firstName, lastName, expDate, cvv, pin, cashBack, status, bankAccount, client);
    }
}